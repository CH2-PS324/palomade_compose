package com.example.palomadeapps.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.palomadeapps.data.PaloRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: PaloRepository): ViewModel(){
    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }

}