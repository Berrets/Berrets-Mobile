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
		@Field("email") email: String,
		@Field("userName") userName: String,
		@Field("fullName") fullName: String,
		@Field("roleUser") roleUser: String,
		@Field("password") password: String
	): RegisterResponse

	@FormUrlEncoded
	@POST("login")
	suspend fun login(
		@Field("userName") email: String,
		@Field("password") password: String
	): LoginResponse

	@FormUrlEncoded
	@POST("get-email")
	suspend fun getEmail(
		@Field("email") email: String
	): RegisterResponse
}