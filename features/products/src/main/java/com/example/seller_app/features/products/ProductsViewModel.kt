package com.example.seller_app.features.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seller_app.core.data.CategoryRepository
import com.example.seller_app.core.data.SubCategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val subCategoryRepository: SubCategoryRepository

) : ViewModel() {

    var currentCategory = MutableStateFlow("")
        private set

    var currentSubCategory = MutableStateFlow("")
        private set

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories = _categories.asStateFlow()

    var subcategories = MutableStateFlow<List<String>>(emptyList())
        private set



    init {
        initializeCategories()

    }


    private fun initializeCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            _categories.value = categoryRepository.getCategories()
            currentCategory.value = _categories.value.firstOrNull().orEmpty()
            subcategories.value = subCategoryRepository.getSubcategoriesByCategoryId(currentCategory.value)
            currentSubCategory.value = subcategories.value.firstOrNull().orEmpty()
        }
    }


    fun updateCategory(category: String) {
        viewModelScope.launch {
            // Fetch from network
            currentCategory.value = category
            subcategories.value = subCategoryRepository.getSubcategoriesByCategoryId(category)
            if (currentSubCategory.value !in subcategories.value) {
                currentSubCategory.value = subcategories.value[0]
            }
            // TODO(Fetch products from network)
        }

    }

    fun updateSubCategory(subCategory: String) {
        viewModelScope.launch {
            currentSubCategory.value = subCategory
            // TODO(Fetch products from network)
        }

    }
}