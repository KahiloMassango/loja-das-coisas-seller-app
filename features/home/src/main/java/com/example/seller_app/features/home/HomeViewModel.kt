package com.example.seller_app.features.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seller_app.core.data.repositories.OrderRepository
import com.example.seller_app.core.data.util.NetworkMonitor
import com.example.seller_app.features.home.model.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    var message: String? by mutableStateOf(null)
        private set

    init {
        loadOrders()
    }

    fun loadOrders() {
        viewModelScope.launch {
            orderRepository.getOrders()
                .onSuccess { response ->
                    _uiState.value = HomeUiState.Success(
                        totalPendingOrders = response.totalPendingOrders,
                        totalDeliveredOrders = response.totalDeliveredOrders,
                        pendingOrders = response.pending,
                        deliveredOrders = response.delivered
                    )
                }
                .onFailure {
                    _uiState.value = HomeUiState.Error(it.message ?: "Error")
                }
        }
    }

    fun tryAgain() {
        if (!networkMonitor.hasNetworkConnection()) {
            message = "Sem conexão com a internet"
            return
        }
        _uiState.value = HomeUiState.Loading
        loadOrders()

    }

    fun messageShown() {
        message = null
    }


}