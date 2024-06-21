package com.capstone.berrets.api.response

import com.google.gson.annotations.SerializedName
import java.nio.Buffer

data class DetectionDataResponse(
	@field:SerializedName("message")
	val message: String,
	@field:SerializedName("data")
	val data: DetectionData,
)
data class DetectionData(
	@field:SerializedName("fileName")
	val fileName: String,
	@field:SerializedName("photo")
	val photo: Buffer,
	@field:SerializedName("createdAt")
	val createdAt: String,
)

data class DetectionDataRequest (
	@field:SerializedName("fileName")
	val fileName: String,
	@field:SerializedName("photo")
	val photo: String,
)