package com.example.palomadeapps.views.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.palomadeapps.data.PaloRepository
import com.example.palomadeapps.data.pref.UserModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PaloRepository) : ViewModel() {

    private val mutableStateFlow = MutableStateFlow(true)
    val isLoading = mutableStateFlow.asStateFlow()

    private val _uiLoginState = MutableStateFlow<UserModel?>(null)
    val uiLoginState: StateFlow<UserModel?> get() = _uiLoginState
    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }
    init {
       getSession()
    }
    fun getSession(){
//        return repository.getSession().collect()
        viewModelScope.launch {
            repository.getSession().collect{
                _uiLoginState.value = it
            }
        }
    }

}