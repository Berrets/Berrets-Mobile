package com.capstone.berrets.view.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.berrets.local.repository.UserRepository
import com.capstone.berrets.model.User
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: UserRepository): ViewModel() {
	fun getSession(): LiveData<User> = repository.getSession().asLiveData()
}