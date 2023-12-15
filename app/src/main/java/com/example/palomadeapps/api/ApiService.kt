package com.example.palomadeapps.api

import com.example.palomadeapps.response.Prediction.PredictResponse
import com.example.palomadeapps.response.auth.LoginResponse
import com.example.palomadeapps.response.auth.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("users/register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("role") role: String,
    ) : RegisterResponse

    @FormUrlEncoded
    @POST("users/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ) : LoginResponse

    @FormUrlEncoded
    @POST("palomade-ml-api-cc5ff2qgca-uc.a.run.app/predict")
    suspend fun predict(
        @Field("classType") classType: String,
        @Field("presentace") presentace: String
    ): PredictResponse
}