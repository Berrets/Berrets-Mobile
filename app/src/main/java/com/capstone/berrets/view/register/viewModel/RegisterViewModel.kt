package com.capstone.berrets.view.register.viewModel

import android.content.Context
import android.credentials.GetCredentialException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.berrets.BuildConfig
import com.capstone.berrets.local.repository.UserRepository
import com.capstone.berrets.view.login.LoginViewModel
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: UserRepository): ViewModel() {

	private val _loginGoogleResponse = MutableLiveData<Boolean>()
	val loginGoogleResponse: LiveData<Boolean> = _loginGoogleResponse

	private val _isEmailRegistered = MutableLiveData<Boolean>()
	val isEmailRegistered: LiveData<Boolean> = _isEmailRegistered


	@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
	fun loginWithGoogle(context: Context) {
		viewModelScope.launch {
			val credentialManager = CredentialManager.create(context)

			val googleIdOption = GetGoogleIdOption.Builder()
				.setFilterByAuthorizedAccounts(false)
				.setServerClientId(BuildConfig.AUTH_WEB_CLIENT_ID)
				.build()

			val request = GetCredentialRequest.Builder()
				.addCredentialOption(googleIdOption)
				.build()

			try {
				val result: GetCredentialResponse = credentialManager.getCredential(context, request)
				repository.handleLoginWithGoogle(result) { isSuccess ->
					_loginGoogleResponse.postValue(isSuccess)
				}
			} catch (e: GetCredentialException) {
				Log.d(TAG, e.message.toString())
				_loginGoogleResponse.postValue(false)
			}
		}
	}

	fun checkEmailIsRegistered(email: String) {
		viewModelScope.launch {
			val result = repository.checkEmailIsRegistered(email)
			_isEmailRegistered.value = result
		}
	}

	companion object {
		private const val TAG = "RegisterViewModel"
	}
}