package com.example.palomadeapps.response.PredicyResponse

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("status")
	val status: Status? = null
)

data class Data(

	@field:SerializedName("precentase")
	val precentase: Int? = null,

	@field:SerializedName("class")
	val jsonMemberClass: String? = null
)

data class Status(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null
)
