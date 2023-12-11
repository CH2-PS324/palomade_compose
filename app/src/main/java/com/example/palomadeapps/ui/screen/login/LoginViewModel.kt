package com.example.palomadeapps.ui.screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.palomadeapps.data.PaloRepository
import com.example.palomadeapps.model.UserModel
import com.example.palomadeapps.response.auth.LoginResponse
import com.example.palomadeapps.ui.common.UiState
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: PaloRepository) : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")

    private val _upload = MutableLiveData<UiState<LoginResponse>>()
    val upload: LiveData<UiState<LoginResponse>> = _upload

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }

    fun login(email: String, password: String,) {
        viewModelScope.launch {
            repository.login(email, password).asFlow().collect{
                _upload.value = it
            }
        }
    }
}