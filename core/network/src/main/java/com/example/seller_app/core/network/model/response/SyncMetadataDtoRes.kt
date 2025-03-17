package com.example.seller_app.core.network.model.response

import com.example.seller_app.core.network.model.response.product.CategoryDtoRes
import com.example.seller_app.core.network.model.response.product.ColorDtoRes
import com.example.seller_app.core.network.model.response.product.SizeDtoRes

data class SyncMetadataDtoRes(
    val categories: List<CategoryDtoRes>,
    val genders: List<GenderDtoRes>,
    val genderCategoryRelations: List<GenderCategoryDtoRes>,
    val colors: List<ColorDtoRes>,
    val sizes: List<SizeDtoRes>
)

data class GenderCategoryDtoRes(
    val genderId: String,
    val categoryId: String,
)
