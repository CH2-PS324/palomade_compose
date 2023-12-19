package com.example.palomadeapps.ui.screen.camera

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.palomadeapps.data.PaloRepository
import com.example.palomadeapps.data.PredictionRepository
import com.example.palomadeapps.data.ResponseState
import com.example.palomadeapps.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File

class CameraViewModel(private val repository: PredictionRepository) : ViewModel() {

    private val _bitmaps = MutableStateFlow<List<Bitmap>>(emptyList())
    val bitmaps = _bitmaps.asStateFlow()

    // Exposing data as ui state to UI (View, Activity, Fragment, etc)
    private val _uiState: MutableStateFlow<CameraUiState> = MutableStateFlow(CameraUiState())
    val uiState: StateFlow<CameraUiState> = _uiState.asStateFlow()



    fun prediction(imageBitmap: Bitmap, description: String) {
        viewModelScope.launch {
            repository.runPrediction(imageBitmap, description).collect {
                when(it) {
                    is ResponseState.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoading = true)
                    }

                    is ResponseState.Success -> {
                        _uiState.value = _uiState.value.copy(prediction = it.data)
                    }

                    is ResponseState.Error -> {
                        _uiState.value = _uiState.value.copy(errorMessage = it.message)
                    }
                }
            }
        }
    }


    fun onTakePhoto(bitmap: Bitmap) {
        _bitmaps.value += bitmap
    }

}