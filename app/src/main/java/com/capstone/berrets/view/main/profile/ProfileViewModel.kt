package com.capstone.berrets.view.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

	private val _name = MutableLiveData<String>().apply {
		value = "Name"
	}

	val name: LiveData<String> = _name


}