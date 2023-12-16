package com.example.palomadeapps.ui.screen.camera

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.palomadeapps.data.PaloRepository
import com.example.palomadeapps.response.Prediction.Data
import com.example.palomadeapps.response.Prediction.PredictResponse
import com.example.palomadeapps.response.Prediction.Status
import com.example.palomadeapps.response.auth.LoginResponse
import com.example.palomadeapps.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.File

class CameraViewModel(private val repository: PaloRepository) : ViewModel() {

    private val _bitmaps = MutableStateFlow<List<Bitmap>>(emptyList())
    val bitmaps = _bitmaps.asStateFlow()

    private val _upload = MutableLiveData<UiState<PredictResponse>>()
    val upload: LiveData<UiState<PredictResponse>> = _upload

    fun prediction(file: File, description: String) {
        viewModelScope
        repository.prediction(file, description)
    }

    fun onTakePhoto(bitmap: Bitmap) {
        _bitmaps.value += bitmap
    }

}