package com.example.palomadeapps.response.Prediction

import com.google.gson.annotations.SerializedName

data class PredictResponse(

	@field:SerializedName("status")
	val status: Status? = null
)

data class Data(

	@field:SerializedName("precentase")
	val precentase: Int? = null,

	@field:SerializedName("classType")
	val classType: String? = null
)

data class Status(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null
)
