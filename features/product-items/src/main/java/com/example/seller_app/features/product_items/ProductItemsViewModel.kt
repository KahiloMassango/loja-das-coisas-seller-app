package com.example.seller_app.features.product_items

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seller_app.core.data.repositories.ColorRepository
import com.example.seller_app.core.data.repositories.ProductRepository
import com.example.seller_app.core.data.repositories.SizeRepository
import com.example.seller_app.core.data.util.NetworkMonitor
import com.example.seller_app.core.model.product.ProductItemRequest
import com.example.seller_app.core.model.product.Size
import com.example.seller_app.features.product_items.model.ProductItemUpdate
import com.example.seller_app.features.product_items.model.ProductItemsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ProductItemsViewModel @Inject constructor(
    private val colorRepository: ColorRepository,
    private val sizeRepository: SizeRepository,
    private val productRepository: ProductRepository,
    private val savedStateHandle: SavedStateHandle,
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    val productId = savedStateHandle.get<String>("productId")!!

    var message: String? by mutableStateOf(null)
        private set

    private val _uiState = MutableStateFlow<ProductItemsUiState>(ProductItemsUiState.Loading)
    val uiState = _uiState.asStateFlow()


    val colors = colorRepository.getAllColors().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5_000L), emptyList()
    )

    private val _sizes = MutableStateFlow<List<Size>>(emptyList())
    val sizes = _sizes.asStateFlow()

    init {
        loadProduct()
    }

    private fun loadProduct() {
        viewModelScope.launch {
            productRepository.getProductById(productId)
                .onSuccess { product ->
                    _uiState.value = ProductItemsUiState.Success(
                        productName = product.name,
                        category = product.category,
                        productItems = product.productItems
                    )
                    _sizes.value = sizeRepository.getSizesByCategory(product.category.id)
                }
                .onFailure {
                    _uiState.value = ProductItemsUiState.Error(it.message ?: "Unknown error")
                }
        }
    }

    fun tryAgain() {
        if(!networkMonitor.hasNetworkConnection()) {
            message = "Sem conexão com a internet"
            return
        }
        viewModelScope.launch {
            _uiState.value = ProductItemsUiState.Loading
            productRepository.getProductById(productId)
                .onSuccess { product ->
                    _uiState.value = ProductItemsUiState.Success(
                        productName = product.name,
                        category = product.category,
                        productItems = product.productItems
                    )
                    _sizes.value = sizeRepository.getSizesByCategory(product.category.id)
                }
                .onFailure {
                    message
                    _uiState.value = ProductItemsUiState.Error(it.message ?: "Unknown error")
                }
        }
    }

    fun messageShown() {
        message = null
    }

    fun addProductItem(productItem: ProductItemRequest) {
        if(!networkMonitor.hasNetworkConnection()) {
            message = "Sem conexão com a internet"
            return
        }
        viewModelScope.launch {
            productRepository.addProductItem(productId, productItem)
                .onSuccess {
                    loadProduct()
                }
                .onFailure { error ->
                    message = error.message
                }

        }
    }

    fun updateProductItem(update: ProductItemUpdate) {
        if(!networkMonitor.hasNetworkConnection()) {
            message = "Sem conexão com a internet"
            return
        }
        viewModelScope.launch {
            productRepository.updateProductItem(
                productId = productId,
                productItemId = update.productItemId,
                stockQuantity = update.stockQuantity,
                price = update.price,
                image = update.image
            )
                .onSuccess {
                    loadProduct()
                }
                .onFailure { error ->
                    message = error.message
                }
        }
    }

    fun deleteVariation(productItemId: String) {
        if(!networkMonitor.hasNetworkConnection()) {
            message = "Sem conexão com a internet"
            return
        }
        viewModelScope.launch {
            productRepository.deleteProductItem(productId, productItemId)
                .onSuccess {
                    loadProduct()
                }
                .onFailure { error ->
                    message = error.message
                }
        }
    }

}