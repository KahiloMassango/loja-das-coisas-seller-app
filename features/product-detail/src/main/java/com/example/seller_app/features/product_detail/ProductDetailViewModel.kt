package com.example.seller_app.features.product_detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seller_app.core.data.repositories.ProductRepository
import com.example.seller_app.features.product_detail.model.DetailUiState
import com.example.seller_app.features.product_detail.model.ProductUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ProductDetailViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val productId = savedStateHandle.get<String>("id")!!

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    lateinit var product: ProductUiState

    init {
        loadProduct()
    }

    private fun loadProduct() {
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
            }.onFailure {
                Log.d("ProductDetailViewModel", "update error: $it")
            }
        }
    }

    fun deleteProduct() {
        viewModelScope.launch {
            productRepository.deleteProduct(productId)
                .onSuccess {
                    Log.d("ProductDetailViewModel", "delete product: success")
                }.onFailure {
                    Log.d("ProductDetailViewModel", "delete error: $it")
                }
        }
    }

}

