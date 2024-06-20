package com.capstone.berrets.api.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
	@field:SerializedName("message")
	val message: String,
	@field:SerializedName("data")
	val data: RegisterData
)

data class RegisterData(
	@field:SerializedName("email")
	val email: String,
	@field:SerializedName("userName")
	val userName: String,
	@field:SerializedName("fullName")
	val fullName: String,
	@field:SerializedName("roleUser")
	val roleUser: String,
	@field:SerializedName("_id")
	val id: String,
	@field:SerializedName("createdAt")
	val createdAt: String,
	@field:SerializedName("__v")
	val v: Int,
)
