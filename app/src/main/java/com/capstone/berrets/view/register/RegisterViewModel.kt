package com.capstone.berrets.view.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.capstone.berrets.api.response.RegisterResponse
import com.capstone.berrets.api.response.ResultState
import com.capstone.berrets.local.repository.UserRepository

class RegisterViewModel(private val repository: UserRepository): ViewModel() {

    fun register(
        email: String,
    ): LiveData<ResultState<RegisterResponse>> = repository.registerUser(email)
}