package com.example.palomadeapps.api

import com.example.palomadeapps.response.Prediction.PredictResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PredictionService {
    @Multipart
    @POST("predict")
    suspend fun runPrediction(
        @Part image: MultipartBody.Part,
        @Part("type") type: RequestBody
    ) : PredictResponse
}