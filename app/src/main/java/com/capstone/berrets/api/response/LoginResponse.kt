package com.capstone.berrets.api.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
	@field:SerializedName("jwtToken")
	val jwtToken: String,
	@field:SerializedName("tokenObject")
	val tokenObject: TokenObject
)

data class TokenObject(
	@field:SerializedName("_id")
	val id: String,
	@field:SerializedName("email")
	val email: String,
	@field:SerializedName("userName")
	val userName: String,
	@field:SerializedName("fullName")
	val fullName: String,
	@field:SerializedName("roleUser")
	val roleUser: String,
)
