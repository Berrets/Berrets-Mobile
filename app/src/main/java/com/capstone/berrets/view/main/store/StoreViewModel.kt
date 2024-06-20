package com.capstone.berrets.view.main.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StoreViewModel : ViewModel() {

	private val _text = MutableLiveData<String>().apply {
		value = "Store Page Coming Soon"
	}
	val text: LiveData<String> = _text
}