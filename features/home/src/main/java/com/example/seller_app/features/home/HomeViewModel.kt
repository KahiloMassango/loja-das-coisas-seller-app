package com.example.seller_app.features.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seller_app.core.data.repositories.CategoryRepository
import com.example.seller_app.core.data.repositories.ColorRepository
import com.example.seller_app.core.data.repositories.GenderRepository
import com.example.seller_app.core.data.SyncManager
import com.example.seller_app.core.data.repositories.OrderRepository
import com.example.seller_app.core.data.repositories.SizeRepository
import com.example.seller_app.features.home.model.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val orderRepository: OrderRepository
): ViewModel() {

    private var _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            loadOrders()
        }
    }

    fun loadOrders() {
        viewModelScope.launch {
            orderRepository.getOrders()
                .onSuccess {
                    _uiState.value = HomeUiState.Success(
                        totalPendingOrders = it.totalPendingOrders,
                        totalDeliveredOrders = it.totalDeliveredOrders,
                        pendingOrders = it.pending,
                        deliveredOrders = it.delivered
                    )
                }
                .onFailure {
                    Log.d("HomeViewModel", "loadOrders: error: $it")
                    _uiState.value = HomeUiState.Error(it.message ?: "Erro desconhecido")
                }
        }
    }
}