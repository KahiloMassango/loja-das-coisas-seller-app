package com.example.seller_app.features.products

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seller_app.core.data.repositories.CategoryRepository
import com.example.seller_app.core.data.repositories.GenderRepository
import com.example.seller_app.core.data.repositories.ProductRepository
import com.example.seller_app.core.data.util.NetworkMonitor
import com.example.seller_app.core.model.product.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ProductsViewModel @Inject constructor(
    private val genderRepository: GenderRepository,
    private val categoryRepository: CategoryRepository,
    private val productRepository: ProductRepository,
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products = _products.asStateFlow()

    var message: String? by mutableStateOf(null)
        private set

    var isRefreshing by mutableStateOf(false)

    var currentGender = MutableStateFlow<String?>(null)
        private set

    var currentCategory = MutableStateFlow<String?>(null)
        private set

    val genders = genderRepository.getGenders()
        .map { it.map { gender -> gender.name } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            emptyList()
        )

    val categories = categoryRepository.getCategories()
        .map { it.map { category -> category.name } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            emptyList()
        )


    fun updateCategory(category: String?) {
        viewModelScope.launch {
            currentCategory.value = category
            getProducts(
                gender = currentGender.value,
                category = currentCategory.value
            )
        }
    }

    fun updateGender(gender: String?) {
        viewModelScope.launch {
            currentGender.value = gender
            getProducts(
                gender = currentGender.value,
                category = currentCategory.value
            )
        }
    }


    fun getProducts(gender: String? = null, category: String? = null) {
        if (!networkMonitor.hasNetworkConnection()) {
            message = "Sem conexão com a internet"
            return
        }
        viewModelScope.launch {
            productRepository.filterProducts(gender = gender, category = category)
                .onSuccess {
                    _products.value = it
                }.onFailure { error ->
                    message = error.message ?: "Algo deu errado"
                }
        }
    }

    fun refresh() {
        if (!networkMonitor.hasNetworkConnection()) {
            message = "Sem conexão com a internet"
            return
        }
        viewModelScope.launch {
            isRefreshing = true
            productRepository.filterProducts(
                gender = currentGender.value,
                category = currentCategory.value
            ).onSuccess {
                _products.value = it
                isRefreshing = false
            }.onFailure { error ->
                isRefreshing = false
                message = error.message ?: "Algo deu errado"
            }
        }
    }

    fun messageShown() {
        message = null
    }
}