package com.capstone.berrets.local.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.capstone.berrets.api.ApiService
import com.capstone.berrets.dummy.DummyData
import com.capstone.berrets.local.preferences.UserPreferences
import com.capstone.berrets.model.PostItem

class PostRepository private constructor(
	private val apiService: ApiService,
) {
	suspend fun getPosts(): LiveData<List<PostItem>> {
		// TODO : Implement real API Calls
		val result = MutableLiveData<List<PostItem>>()
		result.value = DummyData.generateDummyPost()
		Log.d(TAG, "getPosts: $result")
		return result
	}
 	companion object {
		private const val TAG = "PostRepository"
		fun getInstance(
			apiService: ApiService,
		) = PostRepository(apiService)
	}
}