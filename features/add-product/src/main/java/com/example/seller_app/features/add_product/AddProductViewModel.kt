package com.example.seller_app.features.add_product

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seller_app.core.data.repositories.CategoryRepository
import com.example.seller_app.core.data.repositories.ColorRepository
import com.example.seller_app.core.data.repositories.GenderRepository
import com.example.seller_app.core.data.repositories.ProductRepository
import com.example.seller_app.core.data.repositories.SizeRepository
import com.example.seller_app.core.model.Gender
import com.example.seller_app.core.model.product.Category
import com.example.seller_app.core.model.product.ProductItem
import com.example.seller_app.core.model.product.ProductWithVariation
import com.example.seller_app.features.add_product.model.ProductUiState
import dagger.hilt.android.lifecycle.HiltViewModel
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
internal class AddProductViewModel @Inject constructor(
    private val genderRepository: GenderRepository,
    private val categoryRepository: CategoryRepository,
    private val colorRepository: ColorRepository,
    private val sizeRepository: SizeRepository,
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    val colors = colorRepository.getAllColors()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            emptyList()
        )

    val genders = genderRepository.getGenders()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            emptyList()
        )

    val categories = categoryRepository.getCategories()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            emptyList()
        )

    @OptIn(ExperimentalCoroutinesApi::class)
    val size = uiState.mapLatest { state ->
        if (state.category != null) {
            Log.d("Add Viewmodel", "category: ${state.category}")
            sizeRepository.getSizesByCategory(state.category.id)

        } else {
            emptyList()
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )


    fun updateName(name: String) {
        _uiState.update { it.copy(productName = name) }
    }

    fun updateDescription(description: String) {
        _uiState.update { it.copy(description = description) }
    }

    fun updateImage(image: String) {
        if (image != _uiState.value.image) {
            _uiState.update { it.copy(image = image) }
        }
    }

    fun updateGender(gender: Gender) {
        _uiState.update { it.copy(gender = gender) }
        // Clear variation list
        _uiState.update { it.copy(variations = emptyList()) }
    }

    fun updateCategory(category: Category) {
        _uiState.value = _uiState.value.copy(category = category)
        // Clear variation list
        _uiState.update { it.copy(variations = emptyList()) }
    }

    fun updateIsAvailable(value: Boolean) {
        _uiState.value = _uiState.value.copy(isAvailable = value)
    }

    fun addVariation(variation: ProductItem) {
        _uiState.update { it.copy(variations = it.variations + variation) }
    }

    fun removeVariation(index: Int) {
        _uiState.update {
            it.copy(
                variations = it.variations - it.variations[index]
            )
        }
    }

    fun saveProduct() {
        viewModelScope.launch {
            productRepository.addProduct(
                ProductWithVariation(
                    id = "",
                    name = _uiState.value.productName,
                    description = _uiState.value.description,
                    imageUrl = _uiState.value.image,
                    isAvailable = _uiState.value.isAvailable,
                    category = _uiState.value.category!!,
                    gender = _uiState.value.gender!!,
                    productItems = _uiState.value.variations
                )
            ).onSuccess {
                productRepository.addProductItemList(
                    it.id,
                    _uiState.value.variations
                )
                Log.d("AddProductViewModel", "add product: success")
            }
                .onFailure {
                    Log.d("AddProductViewModel", "add product error: ${it.message}")
                }
        }
    }


}

