package com.capstone.berrets.api

import com.capstone.berrets.api.response.LoginResponse
import com.capstone.berrets.api.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
	@FormUrlEncoded
	@POST("register")
	suspend fun register(
		@Field("fullName") name: String,
		@Field("email") email: String,
		@Field("password") password: String
	): RegisterResponse

	@FormUrlEncoded
	@POST("login")
	suspend fun login(
		@Field("userName") email: String,
		@Field("password") password: String
	): LoginResponse
}