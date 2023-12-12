package com.example.palomadeapps.ui.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.palomadeapps.data.PaloRepository
import com.example.palomadeapps.model.ArticelModel
import com.example.palomadeapps.model.UserModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: PaloRepository): ViewModel() {

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
    val groupedHeroes: StateFlow<Map<Char, List<ArticelModel>>> get() = _groupedHeroes
    private val _groupedHeroes = MutableStateFlow(
        repository.getArticel()
            .sortedBy { it.title }
            .groupBy { it.title[0] }
    )
    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}