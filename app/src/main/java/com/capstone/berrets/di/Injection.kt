package com.capstone.berrets.di

import android.content.Context
import com.capstone.berrets.api.ApiConfig
import com.capstone.berrets.local.preferences.UserPreference
import com.capstone.berrets.local.preferences.dataStore
import com.capstone.berrets.local.repository.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {

    fun provideRepository(context: Context) : UserRepository {
        val userPref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { userPref.getSession().first() }
        val apiService = ApiConfig.getApiService(user.token)
        return UserRepository.getInstance(userPref, apiService)
    }