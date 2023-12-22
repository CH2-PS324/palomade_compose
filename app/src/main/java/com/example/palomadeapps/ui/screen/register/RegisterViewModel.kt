package com.example.palomadeapps.ui.screen.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.palomadeapps.data.PaloRepository
import com.example.palomadeapps.response.auth.RegisterResponse
import com.example.palomadeapps.ui.common.UiState
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: PaloRepository): ViewModel() {
    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var role by mutableStateOf("user")

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _upload = MutableLiveData<UiState<RegisterResponse>>()
    val upload: LiveData<UiState<RegisterResponse>> = _upload

    fun uploadData(
        name: String,
        email: String,
        password: String,
        role: String
    ){
        viewModelScope.launch {
            try {
                _isLoading.postValue(true) //untuk set true
                repository.registerAccount(name, email, password, role).asFlow().collect(){
                    _upload.value = it
                }
            } finally {
                _isLoading.postValue(false) //untuk set false
            }
        }
    }
}