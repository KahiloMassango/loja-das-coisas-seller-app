package com.example.seller_app.features.order_detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seller_app.core.data.repositories.OrderRepository
import com.example.seller_app.features.order_detail.model.OrderDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class OrderDetailViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val orderId = savedStateHandle.get<String>("orderId") ?: ""

    private var _uiState = MutableStateFlow<OrderDetailUiState>(OrderDetailUiState.Loading)
    val uiState = _uiState
        .onStart {
            loadOrder()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            OrderDetailUiState.Loading
        )


    fun loadOrder() {
        viewModelScope.launch {
            orderRepository.getOrderById(orderId)
                .onSuccess {
                    _uiState.value = OrderDetailUiState.Success(it)
                }
                .onFailure {
                    it.printStackTrace()
                    _uiState.value = OrderDetailUiState.Error
                }
        }
    }

    fun confirmDelivery() {
        viewModelScope.launch {
            val currentOrderState = (_uiState.value as OrderDetailUiState.Success).order
            orderRepository.confirmDeliveredOrder(orderId)
                .onSuccess {
                    _uiState.value =
                        OrderDetailUiState.Success(currentOrderState.copy(delivered = true))
                }
                .onFailure {
                    Log.d("OrderDetailViewModel", "confirmDeliveredOrder error: $it")
                }
        }
    }


}
