package com.example.seller_app.features.add_product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seller_app.core.data.GenderRepository
import com.example.seller_app.core.data.ColorRepository
import com.example.seller_app.core.data.SizeRepository
import com.example.seller_app.core.data.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(
    private val genderRepository: GenderRepository,
    private val categoryRepository: CategoryRepository,
    private val colorRepository: ColorRepository,
    private val sizeRepository: SizeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    private val _colorOptions = MutableStateFlow<List<String>>(emptyList())
    val colorOptions = _colorOptions.asStateFlow()

    var genders = MutableStateFlow<List<String>>(emptyList())
        private set

    var categories = MutableStateFlow<List<String>>(emptyList())
        private set


    init {
        viewModelScope.launch(Dispatchers.IO) {
            genders.value = genderRepository.getGenders()
            categories.value = categoryRepository.getAllCategories()
            _colorOptions.value = colorRepository.getAllColors()
        }
    }




    @OptIn(ExperimentalCoroutinesApi::class)
    val sizeOptions = uiState.mapLatest { state ->
        if (state.category != null) {
            sizeRepository.getSizesByCategory(state.category)
        } else {
            emptyList()
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )


    fun updateProductName(name: String) {
        _uiState.update { it.copy(productName = name) }
    }

    fun updateDescription(description: String) {
        _uiState.update { it.copy(description = description) }
    }

    fun updateImage(image: String) {
        _uiState.update { it.copy(image = image) }
    }

    fun updateCategory(category: String) {
        _uiState.update { it.copy(gender = category) }
        // Clear variation list
        _uiState.update { it.copy(variations = emptyList()) }
    }

    fun updateSubCategory(subCategory: String) {
        _uiState.update { it.copy(category = subCategory) }
        // Clear variation list
        _uiState.update { it.copy(variations = emptyList()) }
    }

    fun addVariation(variation: VariationItem) {
        _uiState.update { it.copy(variations = it.variations + variation) }
    }

    fun removeVariation(index: Int) {
        _uiState.update {
            it.copy(
                variations = it.variations - it.variations[index]
            )
        }
    }

}

data class ProductUiState(
    val productName: String = "",
    val description: String = "",
    val image: String = "",
    val gender: String? = null,
    val category: String? = null,
    val variations: List<VariationItem> = listOf(VariationItem())
)

data class VariationItem(
    val color: String? = null,
    val image: String? = null,
    var price: String = "1",
    val size: String? = null,
    var quantity: String = "1"
)
