package com.example.palomadeapps.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.palomadeapps.data.PaloRepository
import com.example.palomadeapps.model.ArticelModel
import com.example.palomadeapps.model.OrderReward
import com.example.palomadeapps.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: PaloRepository
) : ViewModel(){
    private val _uiState: MutableStateFlow<UiState<ArticelModel>> =
        MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<ArticelModel>> get() = _uiState

    fun getRewardById(rewardId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderRewardById(rewardId))
        }
    }

//    fun addToCart(reward: Reward, count: Int) {
//        viewModelScope.launch {
//            repository.updateOrderReward(reward.id, count)
//        }
//    }
}