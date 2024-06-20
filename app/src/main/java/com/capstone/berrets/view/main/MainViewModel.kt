package com.capstone.berrets.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.berrets.local.repository.UserRepository
import com.capstone.berrets.model.User
import com.google.firebase.auth.FirebaseUser

class MainViewModel(private val repository: UserRepository): ViewModel() {
	fun getSession(): LiveData<User> = repository.getSession().asLiveData()
}