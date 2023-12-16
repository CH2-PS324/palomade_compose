package com.example.palomadeapps.ui.screen.camera

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.example.palomadeapps.data.PaloRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.File

class CameraViewModel(private val repository: PaloRepository) : ViewModel() {

    private val _bitmaps = MutableStateFlow<List<Bitmap>>(emptyList())
    val bitmaps = _bitmaps.asStateFlow()

    fun prediction(file: File, description: String) {
        repository.prediction(file, description)
    }

    fun onTakePhoto(bitmap: Bitmap) {
        _bitmaps.value += bitmap
    }

}