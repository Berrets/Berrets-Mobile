package com.capstone.berrets.model

data class User(
	// Perlu disesuaikan
	val email: String,
	val fullname: String,
	val username: String,
	val token: String,
	val isLogin: Boolean = false
)
