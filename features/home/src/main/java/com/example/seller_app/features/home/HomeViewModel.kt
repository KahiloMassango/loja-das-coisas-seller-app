package com.example.seller_app.features.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seller_app.core.data.repositories.OrderRepository
import com.example.seller_app.features.home.model.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {

    val uiState = flow {
        emit(HomeUiState.Loading)
        val result = orderRepository.getOrders()
        emit(
            result.fold(
                onSuccess = { orders ->
                    HomeUiState.Success(
                        totalPendingOrders = orders.totalPendingOrders,
                        totalDeliveredOrders = orders.totalDeliveredOrders,
                        pendingOrders = orders.pending,
                        deliveredOrders = orders.delivered
                    )
                },
                onFailure = { error ->
                    Log.d("HomeViewModel", "loadOrders: error: $error")
                    HomeUiState.Error(error.message ?: "Erro desconhecido")
                }
            )
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000L),
        HomeUiState.Loading
    )

}