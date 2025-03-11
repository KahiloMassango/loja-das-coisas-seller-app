package com.example.seller_app.features.product_detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seller_app.core.data.repositories.ProductRepository
import com.example.seller_app.core.data.util.NetworkMonitor
import com.example.seller_app.features.product_detail.model.DetailUiState
import com.example.seller_app.features.product_detail.model.ProductUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ProductDetailViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val networkMonitor: NetworkMonitor,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val productId = savedStateHandle.get<String>("id")!!

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    var message: String? by mutableStateOf(null)
        private set

    var deleted by mutableStateOf(false)
        private set


    lateinit var product: ProductUiState

    init {
        loadProduct()
    }

    private fun loadProduct() {
        _uiState.value = DetailUiState.Loading
        viewModelScope.launch {
            productRepository.getProductById(productId)
                .onSuccess { result ->
                    _uiState.value = DetailUiState.Success(
                        ProductUiState(
                            id = productId,
                            productName = result.name,
                            description = result.description,
                            image = result.imageUrl,
                            gender = result.gender.name,
                            category = result.category,
                            isAvailable = result.isAvailable
                        ).also {
                            product = it
                        }
                    )
                }
                .onFailure {
                    _uiState.value = DetailUiState.Error(it.message ?: "Unknown error")
                }
        }
    }

    fun updateProductName(value: String) {
        if (_uiState.value is DetailUiState.Success) {
            val currentProduct = (_uiState.value as DetailUiState.Success).product
            _uiState.value = DetailUiState.Success(currentProduct.copy(productName = value))
        }
    }

    fun updateDescription(value: String) {
        if (_uiState.value is DetailUiState.Success) {
            val currentProduct = (_uiState.value as DetailUiState.Success).product
            _uiState.value = DetailUiState.Success(currentProduct.copy(description = value))
        }
    }

    fun updateImageUrl(value: String) {
        if (_uiState.value is DetailUiState.Success) {
            val currentProduct = (_uiState.value as DetailUiState.Success).product
            if (value != product.image) {
                _uiState.value = DetailUiState.Success(currentProduct.copy(image = value))
            }
        }
    }

    fun updateIsAvailable(value: Boolean) {
        if (_uiState.value is DetailUiState.Success) {
            val currentProduct = (_uiState.value as DetailUiState.Success).product
            _uiState.value = DetailUiState.Success(currentProduct.copy(isAvailable = value))
        }
    }

    fun updatedProduct() {
        if(!networkMonitor.hasNetworkConnection()) {
            message = "Sem conexão com a internet"
            return
        }
        viewModelScope.launch {
            val state = (_uiState.value as DetailUiState.Success).product
            productRepository.updateProduct(
                productId = productId,
                name = state.productName,
                isAvailable = state.isAvailable,
                description = state.description,
                image = if (product.image != state.image) state.image else null
            ).onSuccess { product ->
                _uiState.value = DetailUiState.Success(
                    ProductUiState(
                        id = productId,
                        productName = product.name,
                        description = product.description,
                        image = product.imageUrl,
                        gender = state.gender,
                        category = state.category,
                        isAvailable = product.isAvailable
                    )
                )
            }.onFailure {ex ->
                message = ex.message ?: "Algo deu errado"
            }
        }
    }

    fun deleteProduct() {
        if(!networkMonitor.hasNetworkConnection()) {
            message = "Sem conexão com a internet"
            return
        }
        viewModelScope.launch {
            productRepository.deleteProduct(productId)
                .onSuccess {
                    deleted = true
                }.onFailure { ex ->
                    message = ex.message ?: "Algo deu errado"
                }
        }
    }

    fun messageShown() {
        message = null
    }

}

