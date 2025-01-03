package com.example.seller_app.core.data

import com.example.seller_app.core.model.Category

interface CategoryRepository {
    fun getCategories(): List<String>
    fun addCategory(category: Category)

}