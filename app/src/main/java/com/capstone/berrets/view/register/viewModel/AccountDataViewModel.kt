package com.capstone.berrets.view.register.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.berrets.api.response.RegisterResponse
import com.capstone.berrets.local.repository.UserRepository
import com.capstone.berrets.model.Register
import kotlinx.coroutines.launch

class AccountDataViewModel(private val repository: UserRepository): ViewModel() {
	private val _registerResponse = MutableLiveData<RegisterResponse>()
	val registerResponse: LiveData<RegisterResponse> = _registerResponse

	private val _isLoading = MutableLiveData<Boolean>()
	val isLoading: LiveData<Boolean> = _isLoading

	fun register(data: Register) {
		_isLoading.value = true
		viewModelScope.launch {
			val result = repository.register(data)
			_registerResponse.value = result
			_isLoading.value = false
		}
	}
}