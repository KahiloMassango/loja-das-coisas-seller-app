package com.example.seller_app.core.data.repositories

import com.example.seller_app.core.model.Gender
import com.example.seller_app.core.network.model.response.GenderCategoryDtoRes
import com.example.seller_app.core.network.model.response.GenderDtoRes
import kotlinx.coroutines.flow.Flow

interface GenderRepository {
    fun getGenders(): Flow<List<Gender>>
    suspend fun sync(genders: List<GenderDtoRes>)
    suspend fun syncGenderCategories(genderCategories: List<GenderCategoryDtoRes>)
}