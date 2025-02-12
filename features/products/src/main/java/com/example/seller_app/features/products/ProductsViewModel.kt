package com.example.seller_app.features.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seller_app.core.data.GenderRepository
import com.example.seller_app.core.data.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val genderRepository: GenderRepository,
    private val categoryRepository: CategoryRepository

) : ViewModel() {

    var currentGender = MutableStateFlow("")
        private set

    var currentCategory = MutableStateFlow("")
        private set

    private val _genders = MutableStateFlow<List<String>>(emptyList())
    val genders = _genders.asStateFlow()

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories = _categories.asStateFlow()



    init {
        initializeCategories()
    }


    private fun initializeCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            _genders.value = genderRepository.getGenders()
            currentGender.value = _genders.value.firstOrNull().orEmpty()
            _categories.value = categoryRepository.getAllCategories()
            currentCategory.value = _categories.value.firstOrNull().orEmpty()
        }
    }


    fun updateCategory(category: String) {
        viewModelScope.launch {
            // Fetch from network
            currentGender.value = category

            // TODO(Fetch products from network)
        }

    }

    fun updateSubCategory(subCategory: String) {
        viewModelScope.launch {
            currentCategory.value = subCategory
            // TODO(Fetch products from network)
        }

    }
}