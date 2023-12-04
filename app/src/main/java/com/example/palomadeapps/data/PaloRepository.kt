package com.example.palomadeapps.data
import androidx.lifecycle.liveData
import com.example.palomadeapps.api.ApiService
import com.example.palomadeapps.data.pref.UserModel
import com.example.palomadeapps.data.pref.UserPref
import com.example.palomadeapps.response.auth.LoginResponse
import com.example.palomadeapps.response.auth.RegisterResponse
import com.example.palomadeapps.ui.common.UiState
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class PaloRepository (
    private val userPreference: UserPref,
    private val apiService: ApiService
){
    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    suspend fun logout() {
        userPreference.logout()
    }
    suspend fun registerAccount(name: String, email: String, password: String, role: String) = liveData {
        emit(UiState.Loading)
        try {
            val successResponse = apiService.register(name, email, password, role)
            emit(UiState.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, RegisterResponse::class.java)
            emit(UiState.Error(errorResponse.toString()))
        } catch (e: Exception) {
            emit(UiState.Error("Error : ${e.message.toString()}"))
        }

    }

    suspend fun login(email: String, password: String) = liveData {
        emit(UiState.Loading)
        try {
            val successResponse = apiService.login(email,password)
            emit(UiState.Success(successResponse))
        }catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, LoginResponse::class.java)
            emit(UiState.Error(errorResponse.toString()))
        } catch (e: Exception) {
            emit(UiState.Error("Error : ${e.message.toString()}"))
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