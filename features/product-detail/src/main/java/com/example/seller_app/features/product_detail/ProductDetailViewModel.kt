package com.example.seller_app.features.product_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seller_app.core.data.ColorRepository
import com.example.seller_app.core.data.SizeRepository
import com.example.seller_app.core.model.product.VariationItem
import com.example.seller_app.features.product_detail.model.VariationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val colorRepository: ColorRepository,
    private val sizeRepository: SizeRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(ProductDetailUiState())
    val uiState = _uiState.asStateFlow()

    private val _selectedVariation = MutableStateFlow<VariationItem?>(null)
    val selectedVariation: StateFlow<VariationItem?> = _selectedVariation.asStateFlow()

    private val _colorOptions = MutableStateFlow<List<String>>(emptyList())
    val colorOptions = _colorOptions.asStateFlow()

    private val _sizeOptions = MutableStateFlow<List<String>>(emptyList())
    val sizeOptions = _sizeOptions.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { ProductDetailUiState() }
            _colorOptions.value = colorRepository.getAllColors()
            _sizeOptions.value = sizeRepository.getSizesByCategory(_uiState.value.category)

        }
    }

    fun updateProductName(name: String) {
        _uiState.update { it.copy(productName = name) }
    }

    fun updateDescription(description: String) {
        _uiState.update { it.copy(description = description) }
    }

    fun updateImage(image: String) {
        if (image.isNotBlank()) {
            _uiState.update { it.copy(image = image) }
        }
    }

    fun updateProduct() {
        // Salvar o produto no banco de dados
    }

    fun selectVariation(variationId: String) {
        val variation = _uiState.value.variations.find { it.id == variationId }
        _selectedVariation.value = variation
    }

    fun updateVariation(updatedItem: VariationItem) {
        // TODO(Upload the updated variation to the server)
        _uiState.update { state ->
            state.copy(
                variations = state.variations.map {
                    if (it.id == updatedItem.id) updatedItem else it
                }
            )
        }
    }

    fun addVariation(variation: VariationState) {
        // TODO(Upload the new variation to the server)

    }

    fun deleteVariation(variationId: String) {
        // TODO(Delete the variation from the server)
        _uiState.update { state ->
            state.copy(
                variations = state.variations.filter { it.id != variationId }
            )
        }
    }

    fun clearSelectedVariation() {
        _selectedVariation.value = null
    }
}

data class ProductDetailUiState(
    val productName: String = "Calça Jeans Rasgada",
    val description: String = "Calça confortável, de algodão",
    val image: String = "https://images.tcdn.com.br/img/img_prod/769687/calca_jeans_masculina_slim_algodao_com_elastano_40_e_44_wolfgan_1439_variacao_9653_1_6b0685f12cef1f1e7ef4dbaf36e6a64c.jpg",
    val gender: String = "Homens",
    val category: String = "Roupas",
    val variations: List<VariationItem> = listOf(VariationItem("", "Preto", "", 5.800, "XL", 6))
)