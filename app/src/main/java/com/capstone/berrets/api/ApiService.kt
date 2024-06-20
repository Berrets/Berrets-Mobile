package com.capstone.berrets.api

import com.capstone.berrets.api.response.ExistEmailRequest
import com.capstone.berrets.api.response.ExistEmailResponse
import com.capstone.berrets.api.response.LoginRequest
import com.capstone.berrets.api.response.LoginResponse
import com.capstone.berrets.api.response.RegisterRequest
import com.capstone.berrets.api.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
	@POST("register")
	suspend fun register(
		@Body dataRegister: RegisterRequest
	): RegisterResponse

	@POST("login")
	suspend fun login(
		@Body dataLogin: LoginRequest
	): LoginResponse

	@POST("get-email")
	suspend fun getEmail(
		@Body email: ExistEmailRequest
	): ExistEmailResponse
}