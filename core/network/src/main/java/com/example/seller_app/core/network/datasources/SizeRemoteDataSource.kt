package com.example.seller_app.core.network.datasources

import com.example.seller_app.core.network.model.response.product.SizeDtoRes

interface SizeRemoteDataSource {
    suspend fun fetchSizes(): Result<List<SizeDtoRes>>
}