package com.capstone.berrets.view.main.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StoreViewModel : ViewModel() {

	private val _text = MutableLiveData<String>().apply {
		value = "This is dashboard Fragment"
	}
	val text: LiveData<String> = _text
}