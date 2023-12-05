package com.example.palomadeapps.ui.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.palomadeapps.data.PaloRepository
import com.example.palomadeapps.data.pref.UserModel
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: PaloRepository): ViewModel() {

//    fun getSession(): LiveData<UserModel> {
//        return repository.getSession().asLiveData()
//    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}