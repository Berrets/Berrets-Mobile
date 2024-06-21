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
	private val _isEmailRegistered = MutableLiveData<Boolean>()
	val isEmailRegistered: LiveData<Boolean> = _isEmailRegistered

	private val _isLoading = MutableLiveData<Boolean>()
	val isLoading: LiveData<Boolean> = _isLoading

	fun checkEmailIsRegistered(email: String) {
		_isLoading.value = true
		viewModelScope.launch {
			val result = repository.checkEmailIsRegistered(email)
			_isEmailRegistered.value = result
			_isLoading.value = false
		}
	}

	companion object {
		private const val TAG = "RegisterViewModel"
	}
}