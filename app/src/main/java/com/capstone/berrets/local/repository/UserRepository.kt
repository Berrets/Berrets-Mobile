package com.capstone.berrets.local.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.capstone.berrets.api.ApiService
import com.capstone.berrets.api.response.LoginResponse
import com.capstone.berrets.api.response.RegisterResponse
import com.capstone.berrets.api.response.ResultState
import com.capstone.berrets.local.preferences.UserPreference
import com.capstone.berrets.model.User
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class UserRepository private constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService
) {
    fun registerUser(
        name: String,
        email: String,
        password: String
    ): LiveData<ResultState<RegisterResponse>> = liveData {
        emit(ResultState.Loading)
        try {
            val client = apiService.registerUser(name, email, password)
            emit(ResultState.Success(client))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            errorBody.message?.let {
                ResultState.Error(it)
            }?.let { emit(it) }
        }
    }

    fun loginUser(
        email: String,
        password: String
    ) = liveData {
        emit(ResultState.Loading)
        try {
            val client = apiService.loginUser(email, password)
            emit(ResultState.Success(client))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, LoginResponse::class.java)
            errorBody.message.let {
                ResultState.Error(it)
            }.let { emit(it) }
        }
    }

    suspend fun saveSession(user: User) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<User> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService
        ): UserRepository = instance ?: synchronized(this) {
            instance ?: UserRepository(userPreference, apiService)
        }.also { instance = it }
    }
}