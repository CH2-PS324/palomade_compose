package com.example.palomadeapps.ui.screen.camera

import com.example.palomadeapps.model.Prediction

// Read this!: https://developer.android.com/topic/architecture/ui-layer#define-ui-state
data class CameraUiState(
    val isLoading: Boolean = false,
    val prediction: Prediction? = null,
    val errorMessage: String? = null
)
