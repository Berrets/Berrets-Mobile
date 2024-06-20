package com.capstone.berrets.di

import android.content.Context
import com.capstone.berrets.api.ApiConfig
import com.capstone.berrets.local.preferences.UserPreferences
import com.capstone.berrets.local.preferences.dataStore
import com.capstone.berrets.local.repository.PostRepository
import com.capstone.berrets.local.repository.UserRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
	fun provideUserRepository(context: Context): UserRepository {
		val preferences = UserPreferences.getInstance(context.dataStore)
		val user = runBlocking { preferences.getSession().first()  }
		val apiService = ApiConfig.getApiService(user.token)
		val firebaseAuth = Firebase.auth
		return UserRepository.getInstance(apiService, preferences, firebaseAuth)
	}
	fun providePostRepository(context: Context): PostRepository {
		val apiService = ApiConfig.getApiService("token")
		return PostRepository.getInstance(apiService)
	}
}