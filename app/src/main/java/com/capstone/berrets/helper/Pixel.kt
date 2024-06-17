package com.capstone.berrets.helper

import android.content.Context

@Suppress("UNCHECKED_CAST")
fun <T> T.dp(context: Context): T {
	val resources = context.resources
	val density = resources.displayMetrics.density
	return when (this) {
		is Int -> (this * density).toInt() as T
		is Float -> (this * density) as T
		else -> throw UnsupportedOperationException("Unsupported type")
	}
}