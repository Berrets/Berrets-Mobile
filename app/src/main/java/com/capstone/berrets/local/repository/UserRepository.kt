package com.capstone.berrets.local.repository

import android.util.Log
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialResponse
import androidx.lifecycle.LiveData
import com.capstone.berrets.api.ApiService
import com.capstone.berrets.local.preferences.UserPreferences
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class UserRepository private constructor(
	private val apiService: ApiService,
	private val userPreferences: UserPreferences,
	private val firebaseAuth: FirebaseAuth
) {
	// SESSION
	suspend fun getAuthProviderSession(): String {
		val authProvider = userPreferences.getAuthProvider()
		if (authProvider == "google") {
			return "google"
		} else if (authProvider == "berrets") {
			return "berrets"
		}
		return ""
	}

	fun getGoogleUser(): FirebaseUser? {
		return firebaseAuth.currentUser
	}

	// REGISTER
	suspend fun checkEmailIsRegistered(email: String): Boolean {
		return true
	}

	// LOGIN
	suspend fun handleLoginWithGoogle(result: GetCredentialResponse, callback: (Boolean) -> Unit) {
		when (val credential = result.credential) {
			is CustomCredential -> {
				if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
					try {
						val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
						firebaseAuthWithGoogle(googleIdTokenCredential.idToken, callback)
						return
					} catch (e: GoogleIdTokenParsingException) {
						Log.e(TAG, "Received an invalid google id token response", e)
					}
				} else {
					Log.e(TAG, "Unexpected credential type: ${credential.type}")
				}
			}
			else -> {
				Log.e(TAG, "Unexpected credential type: ${credential.type}")
			}
		}
	}

	// LOGOUT
	fun handleLogoutWithFirebase() {
		firebaseAuth.signOut()
	}

	// FIREBASE
	private suspend fun firebaseAuthWithGoogle(idToken: String, callback: (Boolean) -> Unit) {
		val credential: AuthCredential = GoogleAuthProvider.getCredential(idToken, null)
		firebaseAuth.signInWithCredential(credential)
			.addOnCompleteListener { task ->
				callback(task.isSuccessful)
			}
		userPreferences.saveAuthProvider("google")
	}

	companion object {
		private const val TAG = "UserRepository"
		fun getInstance(
			apiService: ApiService,
			userPreferences: UserPreferences,
			firebaseAuth: FirebaseAuth
		): UserRepository = UserRepository(apiService, userPreferences, firebaseAuth)
	}
}