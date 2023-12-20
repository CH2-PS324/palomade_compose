package com.example.palomadeapps.data

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import android.util.Log
import androidx.compose.ui.platform.isDebugInspectorInfoEnabled

import com.example.palomadeapps.api.ApiService
import com.example.palomadeapps.api.PredictionService
import com.example.palomadeapps.model.Prediction
import com.example.palomadeapps.model.mapper.ModelMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

private const val TAG = "PredictionRepository"

class PredictionRepository(
    val context: Context,
    private val predictionService: PredictionService
) {
    suspend fun runPrediction(imageBitmap: Bitmap, type: String): Flow<ResponseState<Prediction>> {
        return flow {
            emit(value = ResponseState.Loading(isLoading = true))
            try {
                val file = createTemporaryFile(imageBitmap)
                val reqImageFile = file.asRequestBody(contentType = "image/*".toMediaType())
                val reqType = type.toRequestBody(contentType = "text/plain".toMediaType())
                val formData = MultipartBody.Part.createFormData(
                    "image",
                    file.name,
                    reqImageFile)

                val response = predictionService.runPrediction(formData, reqType)
                Log.i(TAG, "runPrediction: $response")
                emit(ResponseState.Success(ModelMapper.toPrediction(response)))
            } catch (e: Exception) {
                emit(ResponseState.Error(message = e.message.toString()))
                if (e is HttpException) {
                    Log.e(TAG, "runPrediction yahaha: ${e.response()?.message().toString()}")
                }
                Log.e(TAG, "runPrediction yahaha: ${e.message.toString()}")
            }
        }
    }

    suspend fun runPredictionBrondolan(imageBitmap: Bitmap, type: String): Flow<ResponseState<Prediction>> {
        return flow {
            emit(value = ResponseState.Loading(isLoading = true))
            try {
                val file = createTemporaryFile(imageBitmap)
                val reqImageFile = file.asRequestBody(contentType = "image/*".toMediaType())
                val reqType = type.toRequestBody(contentType = "text/plain".toMediaType())
                val formData = MultipartBody.Part.createFormData(
                    "image",
                    file.name,
                    reqImageFile)

                val response = predictionService.runPredictionBrondolan(formData, reqType)
                Log.i(TAG, "runPrediction: $response")
                emit(ResponseState.Success(ModelMapper.toPrediction(response)))
            } catch (e: Exception) {
                emit(ResponseState.Error(message = e.message.toString()))
                if (e is HttpException) {
                    Log.e(TAG, "runPrediction yahaha: ${e.response()?.message().toString()}")
                }
                Log.e(TAG, "runPrediction yahaha: ${e.message.toString()}")
            }
        }
    }

    private fun createTemporaryFile(bitmap: Bitmap): File {

        val file = File(
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            "${System.currentTimeMillis()}_image.jpg"
        )

        val byteOutputStream: ByteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, byteOutputStream)
        val bitmapData: ByteArray = byteOutputStream.toByteArray()
        try {
            val fileOutputStream = FileOutputStream(file)
            fileOutputStream.write(bitmapData)
            fileOutputStream.flush()
            fileOutputStream.close()
        } catch (e: Exception) {
            Log.e(TAG, "createTempFile: ${e.message}", )
        }

        return file
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: PredictionRepository? = null

        fun getInstance(context: Context, predictionService: PredictionService): PredictionRepository {
            return instance ?: synchronized(lock = this) {
                instance ?: PredictionRepository(context.applicationContext, predictionService)
            }.also {
                instance = it
            }
        }
    }
}