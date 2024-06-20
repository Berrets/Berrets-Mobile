package com.capstone.berrets.local.repository

import android.util.Log
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.capstone.berrets.api.ApiService
import com.capstone.berrets.api.response.ExistEmailRequest
import com.capstone.berrets.api.response.ExistEmailResponse
import com.capstone.berrets.api.response.LoginRequest
import com.capstone.berrets.api.response.LoginResponse
import com.capstone.berrets.api.response.RegisterRequest
import com.capstone.berrets.api.response.RegisterResponse
import com.capstone.berrets.local.preferences.UserPreferences
import com.capstone.berrets.model.Register
import com.capstone.berrets.model.User
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class UserRepository private constructor(
	private val apiService: ApiService,
	private val userPreferences: UserPreferences,
) {
	// SESSION
	private suspend fun saveSession(user: User) {
		userPreferences.saveSession(user)
	}

	fun getSession(): Flow<User> {
		return userPreferences.getSession()
	}

	// REGISTER
	suspend fun checkEmailIsRegistered(email: String): Boolean {
		try {
			val response = apiService.getEmail(ExistEmailRequest(email))
			Log.d(TAG, "checkEmailIsRegistered: ${response.message}")
			Log.d(TAG, (response.message != "Email not registered").toString())
			return false
		} catch (e: HttpException) {
			val errorBody = e.response()?.errorBody()?.string()
			val response = Gson().fromJson(errorBody, ExistEmailResponse::class.java)
			Log.e(TAG, "checkEmailIsRegistered: ${response.message}")
			return response.message != "Email not registered"
		}
	}

	suspend fun register(data: Register): RegisterResponse {
		try {
			val response = apiService.register(
				RegisterRequest(
					email = data.email,
					userName = data.userName,
					fullName = data.fullName,
					password = data.password,
					roleUser = data.roleUser
				)
			)
			val loginResponse = apiService.login(
				LoginRequest(
					email = data.email,
					password = data.password
				)
			)

			 saveSession(
				 User(
				 	email = data.email,
					 fullname = data.fullName,
					 username = data.userName,
					 token = loginResponse.jwtToken,
					 isLogin = true,
				 )
			 )

			return response
		} catch (e: HttpException) {
			val errorBody = e.response()?.errorBody()?.string()
			val response = Gson().fromJson(errorBody, RegisterResponse::class.java)

			return response
		}
	}

	// LOGIN
	suspend fun login(email: String, password: String): LoginResponse {
		try {
			val response = apiService.login(
				LoginRequest(
					email = email,
					password = password
				)
			)

			saveSession(
				User(
					email = email,
					fullname = response.tokenObject.fullName,
					username = response.tokenObject.userName,
					token = response.jwtToken,
					isLogin = true,
				)
			)

			return response
		} catch (e: HttpException) {
			val errorBody = e.response()?.errorBody()?.string()
			val response = Gson().fromJson(errorBody, LoginResponse::class.java)

			return response
		}
	}

	// LOGOUT
	companion object {
		private const val TAG = "UserRepository"

		@Volatile
		private var INSTANCE: UserRepository? = null

		fun getInstance(
			apiService: ApiService,
			userPreferences: UserPreferences,
		): UserRepository {
			return INSTANCE ?: synchronized(this) {
				INSTANCE ?: UserRepository(apiService, userPreferences).also { INSTANCE = it }
			}
		}
	}
}