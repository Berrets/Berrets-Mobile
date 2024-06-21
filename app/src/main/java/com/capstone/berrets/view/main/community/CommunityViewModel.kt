package com.capstone.berrets.view.main.community

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.berrets.local.repository.PostRepository
import com.capstone.berrets.model.PostItem
import kotlinx.coroutines.launch

class CommunityViewModel(private val repository: PostRepository) : ViewModel() {
	private val _posts = MutableLiveData<List<PostItem>>()
	val posts: LiveData<List<PostItem>> = _posts

	private val _isLoading = MutableLiveData<Boolean>()
	val isLoading: LiveData<Boolean> = _isLoading

	fun getPosts() {
		Log.d(TAG, "getPosts: called")
		_isLoading.value = true
		viewModelScope.launch {
			val posts = repository.getPosts()
			_posts.postValue(posts.value)
			_isLoading.value = false
		}
	}

	companion object {
		private const val TAG = "CommunityViewModel"
	}
}