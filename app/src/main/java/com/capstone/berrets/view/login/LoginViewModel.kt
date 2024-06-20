package com.capstone.berrets.view.login

import android.app.Activity
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
import com.capstone.berrets.api.response.LoginResponse
import com.capstone.berrets.local.repository.UserRepository
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

	private val _loginResponse = MutableLiveData<LoginResponse>()
	val loginResponse: LiveData<LoginResponse> = _loginResponse

	private val _isLoading = MutableLiveData<Boolean>()
	val isLoading: LiveData<Boolean> = _isLoading

	fun login(email: String, password: String) {
		_isLoading.value = true
		viewModelScope.launch {
			val result = repository.login(email, password)
			_loginResponse.value = result
			_isLoading.value = false
		}
	}

	companion object {
		private const val TAG = "LoginViewModel"
	}
}