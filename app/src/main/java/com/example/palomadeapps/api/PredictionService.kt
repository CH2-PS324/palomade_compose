package com.example.palomadeapps.api

import com.example.palomadeapps.response.Prediction.PredictResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PredictionService {
    @Multipart
    @POST("predict-bongkahan")
    suspend fun runPrediction(
        @Part image: MultipartBody.Part
    ) : PredictResponse

    @Multipart
    @POST("predict-brondolan")
    suspend fun runPredictionBrondolan(
        @Part image: MultipartBody.Part
    ) : PredictResponse
}