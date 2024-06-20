package com.capstone.berrets.view.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.berrets.local.repository.UserRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: UserRepository): ViewModel() {
	private val _authProvider = MutableLiveData<String>()
	val authProvider: LiveData<String> = _authProvider

	init {
		viewModelScope.launch {
			val authProvider = repository.getAuthProviderSession()
			_authProvider.postValue(authProvider)
		}
	}

	fun getGoogleUser(): FirebaseUser? {
		return repository.getGoogleUser()
	}
}