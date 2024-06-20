package com.capstone.berrets.api.response

import com.google.gson.annotations.SerializedName

data class ExistEmailResponse (
	@field:SerializedName("message")
	val message: String,
)

data class ExistEmailRequest(
	@SerializedName("email")
	val email: String
)