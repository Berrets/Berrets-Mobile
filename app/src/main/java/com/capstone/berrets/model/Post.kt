package com.capstone.berrets.model

data class PostItem(
	val id: Int,
	val name: String,
	val userName: String,
	val time: String,
	val content : String,
	val like: Int,
	val comment: Int,
	val bookmark: Int,
	val profileImage: String
)
