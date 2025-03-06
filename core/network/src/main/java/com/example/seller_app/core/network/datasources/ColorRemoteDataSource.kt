package com.example.seller_app.core.network.datasources

import com.example.seller_app.core.network.model.response.product.ColorDtoRes

interface ColorRemoteDataSource {
   suspend fun fetchColors(): Result<List<ColorDtoRes>>
}