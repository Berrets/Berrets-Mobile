package com.capstone.berrets.helper

import android.util.Log
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

fun Date.timeOfDay(): TimeOfDay {
	val calendar = Calendar.getInstance(TimeZone.getDefault())
	calendar.time = this
	val hour = calendar.get(Calendar.HOUR_OF_DAY)
	Log.d("TimeOfDay", "hour: $hour")
	return when (hour) {
		in 5..12 -> TimeOfDay.MORNING
		in 12..16 -> TimeOfDay.AFTERNOON
		else -> TimeOfDay.NIGHT
	}
}