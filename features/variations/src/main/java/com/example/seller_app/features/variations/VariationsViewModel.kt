package com.example.seller_app.features.variations

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seller_app.core.data.repositories.ColorRepository
import com.example.seller_app.core.data.repositories.ProductRepository
import com.example.seller_app.core.data.repositories.SizeRepository
import com.example.seller_app.core.model.product.ProductItem
import com.example.seller_app.core.model.product.Size
import com.example.seller_app.features.variations.model.VariationsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class VariationsViewModel @Inject constructor(
    private val colorRepository: ColorRepository,
    private val sizeRepository: SizeRepository,
    private val productRepository: ProductRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val productId = savedStateHandle.get<String>("productId")!!

    private val _uiState = MutableStateFlow<VariationsUiState>(VariationsUiState.Loading)
    val uiState = _uiState.asStateFlow()


    val colors = colorRepository.getAllColors().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5_000L), emptyList()
    )

    private val _sizes = MutableStateFlow<List<Size>>(emptyList())
    val sizes = _sizes.asStateFlow()

    init {
        loadProduct()
    }

    fun loadProduct() {
        viewModelScope.launch {
            productRepository.getProductById(productId)
                .onSuccess { product ->
                    _uiState.value = VariationsUiState.Success(
                        productName = product.name,
                        category = product.category,
                        productItems = product.productItems
                    )
                    _sizes.value = sizeRepository.getSizesByCategory(product.category.id)
                }
                .onFailure {
                    _uiState.value = VariationsUiState.Error(it.message ?: "Unknown error")
                    Log.d("ProductDetailViewModel", "${it.message} - $productId")
                }
        }
    }

    fun addProductItem(productItem: ProductItem) {
        viewModelScope.launch {
            productRepository.addProductItem(productId, productItem)
                .onSuccess {
                    Log.d("VariationsViewModel", "add variation: success")
                    loadProduct()
                }
                .onFailure {
                    Log.d("VariationsViewModel", "add variation: $it")
                }

        }
    }

    fun updateProductItem(productItem: ProductItem) {
        viewModelScope.launch {
            productRepository.updateProductItem(productId, productItem)
                .onSuccess {
                    Log.d("VariationsViewModel", "update variation: success")
                    loadProduct()
                }
                .onFailure {
                    Log.d("VariationsViewModel", "update variation: $it")
                }
        }
    }

    fun deleteVariation(productItemId: String) {
        viewModelScope.launch {
            Log.d("VariationsViewModel","delete variation: $productItemId - $productId")
            productRepository.deleteProductItem(productId, productItemId)
                .onSuccess {
                    Log.d("VariationsViewModel", "delete variation: success")
                    loadProduct()
                }
                .onFailure {
                    Log.d("VariationsViewModel", "delete error variation: $it")
                }
        }
    }

}