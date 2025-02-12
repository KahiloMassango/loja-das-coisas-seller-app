package com.example.seller_app.core.data

interface CategoryRepository {
    suspend fun getAllCategories(): List<String>
    fun addCategory(category: String)

}