package com.capstone.berrets.di

import android.content.Context
import com.capstone.berrets.api.ApiConfig
import com.capstone.berrets.local.repository.PostRepository

object Injection {
	fun providePostRepository(context: Context): PostRepository {
		// TODO : Need to properly inject this
		val apiService = ApiConfig.getApiService("token")
		return PostRepository.getInstance(apiService)
	}
}