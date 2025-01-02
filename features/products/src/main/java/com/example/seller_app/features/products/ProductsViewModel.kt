package com.example.seller_app.features.products

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(

): ViewModel()  {

    val categories = listOf("Mulheres", "Homens", "Infantil", "Cosméticos")
    val subCategoryMap = mapOf(
        "Mulheres" to listOf("Roupas", "Calçados", "Acessórios"),
        "Homens" to listOf("Roupas", "Calçados", "Acessórios"),
        "Infantil" to listOf("Roupas", "Calçados", "Acessórios"),
        "Cosméticos" to listOf("SkinCare", "Maquiagem", "Perfumes")
    )
    val currentCategory = MutableStateFlow(categories[0])
    val currentSubCategory = MutableStateFlow(subCategoryMap[currentCategory.value]!![0])

    val subCategories = MutableStateFlow(subCategoryMap[currentCategory.value]!!)

    fun updateCategory(category: String) {
        currentCategory.value = category
        subCategories.value = subCategoryMap[category]!!
        currentSubCategory.value = subCategoryMap[category]!![0]

    }
    fun updateSubCategory(subCategory: String) {
        currentSubCategory.value = subCategory
    }
}