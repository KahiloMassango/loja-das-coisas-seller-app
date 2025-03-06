package com.example.seller_app.core.network.datasources

import com.example.seller_app.core.network.model.response.product.CategoryDtoRes

interface CategoryRemoteDataSource {
    suspend fun fetchCategories(): Result<List<CategoryDtoRes>>

}