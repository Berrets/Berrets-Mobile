package com.capstone.berrets.model

data class User(
	// Perlu disesuaikan
	val email: String,
	val token: String,
	val role: String,
	val isLogin: Boolean = false
)
