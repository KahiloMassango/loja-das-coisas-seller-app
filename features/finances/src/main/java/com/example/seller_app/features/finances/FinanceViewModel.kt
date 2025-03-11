package com.example.seller_app.features.finances

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seller_app.core.data.repositories.FinanceRepository
import com.example.seller_app.core.data.util.NetworkMonitor
import com.example.seller_app.core.model.FinanceStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class FinanceViewModel @Inject constructor(
    private val financeRepository: FinanceRepository,
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    private var _uiState = MutableStateFlow<FinanceUiState>(FinanceUiState.Loading)
    val uiState = _uiState.asStateFlow()

    var message: String? by mutableStateOf(null)
        private set

    init {
        loadStatus()
    }

    fun loadStatus() {
        viewModelScope.launch {
            financeRepository.getFinanceStatus()
                .onSuccess { result ->
                    _uiState.value = FinanceUiState.Success(result)
                }
                .onFailure { e ->
                    _uiState.value = FinanceUiState.Error(e.message ?: "Error")
                }
        }
    }

    fun requestWithdraw() {
        if (!networkMonitor.hasNetworkConnection()) {
            message = "Sem conexão com a internet"
            return
        }
        viewModelScope.launch {
            financeRepository.requestWithdraw()
                .onSuccess {
                    loadStatus()
                }
                .onFailure { ex ->
                    message = ex.message ?: "Algo deu errado!"
                }
        }
    }

    fun messageShown() {
        message = null
    }
}

internal sealed class FinanceUiState {
    data object Loading : FinanceUiState()
    data class Error(val message: String) : FinanceUiState()
    data class Success(val financeStatus: FinanceStatus) : FinanceUiState()
}