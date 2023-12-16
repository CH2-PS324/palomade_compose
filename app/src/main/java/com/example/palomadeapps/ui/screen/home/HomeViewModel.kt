package com.example.palomadeapps.ui.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.palomadeapps.data.PaloRepository
import com.example.palomadeapps.model.ArticelModel
import com.example.palomadeapps.model.OrderReward
import com.example.palomadeapps.model.UserModel
import com.example.palomadeapps.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: PaloRepository): ViewModel() {

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
    private val _uiState: MutableStateFlow<UiState<OrderReward>> =
        MutableStateFlow(UiState.Loading)

    private val _groupedHeroes = MutableStateFlow(
        repository.getHeroes()
//            .sortedBy { it.title }
            .groupBy { it.title[0] }
    )
    val groupedHeroes: StateFlow<Map<Char, List<ArticelModel>>> get() = _groupedHeroes
    val uiState: StateFlow<UiState<OrderReward>> get() = _uiState

//    fun getRewardById(rewardId: Long) {
//        viewModelScope.launch {
//            _uiState.value = UiState.Loading
//            _uiState.value = UiState.Success(repository.getOrderRewardById(rewardId))
//        }
//    }

//    fun addToCart(reward: Reward, count: Int) {
//        viewModelScope.launch {
//            repository.updateOrderReward(reward.id, count)
//        }
//    }
    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}