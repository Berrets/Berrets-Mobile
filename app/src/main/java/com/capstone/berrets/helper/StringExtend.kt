package com.capstone.berrets.helper

fun String.getUsernameInEmail(): String {
	return this.substringBefore("@")
}