package com.example.palomadeapps.data
import androidx.lifecycle.liveData
import com.example.palomadeapps.api.ApiService
import com.example.palomadeapps.data.dummy.ArticelData
import com.example.palomadeapps.model.UserModel
import com.example.palomadeapps.data.pref.UserPref
import com.example.palomadeapps.model.ArticelModel
import com.example.palomadeapps.model.OrderReward
import com.example.palomadeapps.model.Prediction
import com.example.palomadeapps.response.Prediction.PredictResponse
import com.example.palomadeapps.response.auth.LoginResponse
import com.example.palomadeapps.response.auth.RegisterResponse
import com.example.palomadeapps.ui.common.UiState
import com.google.gson.Gson

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.File

class PaloRepository (
    private val userPreference: UserPref,
    private val apiService: ApiService
){

    private val orderRewards = mutableListOf<OrderReward>()
    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    fun getHeroes(): List<ArticelModel> {
        return ArticelData.dummyArticel
    }

    fun getAllRewards(): Flow<List<OrderReward>> {
        return flowOf(orderRewards)
    }

    fun getOrderRewardById(rewardId: Long): ArticelModel {
        return ArticelData.dummyArticel.first {
            it.id == rewardId
        }
    }
    suspend fun logout() {
        userPreference.logout()
    }

    suspend fun login(email: String, password: String) = liveData {
        emit(UiState.Loading)
        try {
            val successResponse = apiService.login(email,password)
            emit(UiState.Success(successResponse))
        }catch (e: retrofit2.HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, LoginResponse::class.java)
            emit(UiState.Error(errorResponse.toString()))
        } catch (e: Exception) {
            emit(UiState.Error("Error : ${e.message.toString()}"))
        }
    }

    suspend fun registerAccount(name: String, email: String, password: String, role: String) = liveData {
        emit(UiState.Loading)
        try {
            val successResponse = apiService.register(name, email, password, role)
            emit(UiState.Success(successResponse))
        } catch (e: retrofit2.HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, RegisterResponse::class.java)
            emit(UiState.Error(errorResponse.toString()))
        } catch (e: Exception) {
            emit(UiState.Error("Error : ${e.message.toString()}"))
        }
    }

    //PREDICTION API RESPONSE
//    fun prediction(imageFile: File, type: String) = liveData{
//        emit(UiState.Loading)
//        val requestBody = type.toRequestBody("text/plain".toMediaType())
//        val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
//        val multipartBody = MultipartBody.Part.createFormData(
//            "photo",
//            imageFile.name,
//            requestImageFile
//        )
//
//        try {
//            val successResponse =
//                apiService.predict(multipartBody, requestBody)
//            emit(UiState.Success(successResponse))
//        } catch (e: HttpException) {
//            val errorBody = e.response()?.errorBody()?.string()
//
//            val errorResponse = Gson().fromJson(errorBody, PredictResponse::class.java)
//            emit(UiState.Error(errorResponse.toString()))
//        } catch (e: Exception) {
//            emit(UiState.Error("Error : ${e.message.toString()}"))
//        }
//    }

    suspend fun getPrediction(image: File, type: String): Flow<UiState<Prediction>> {
        return flow {
            emit(UiState.Loading)
            val type: RequestBody = type.toRequestBody(contentType = "text/plain".toMediaType())
            val requestImageFile = image.asRequestBody(contentType = "image/jpeg".toMediaType())
            val multipartBody = MultipartBody.Part.createFormData(
                "photo",
                image.name,
                requestImageFile
            )

            try {
//                val response = apiService.predict(multipartBody, type)
//                emit(UiState.Success(data = ModelMapper.toPrediction(response)))
            } catch (e: Exception) {
                emit(UiState.Error(errorMessage = "${e.message}"))
            }
        }
    }

//    fun getSession(): Flow<UserModel> {
//        return userPreference.getSession()
//    }

    companion object {
        @Volatile
        private var instance: PaloRepository? = null
        fun getInstance(
            userPreference: UserPref,
            apiService: ApiService
        ): PaloRepository =
            instance ?: synchronized(this) {
                instance ?: PaloRepository(userPreference, apiService)
            }.also { instance = it }
    }
}