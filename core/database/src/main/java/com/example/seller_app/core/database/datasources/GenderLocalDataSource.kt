package com.example.seller_app.core.database.datasources

import com.example.seller_app.core.database.model.GenderCategory
import com.example.seller_app.core.database.model.GenderEntity
import kotlinx.coroutines.flow.Flow

interface GenderLocalDataSource {
    suspend fun upsertGenders(genders: List<GenderEntity>)
    suspend fun saveGenderCategories(genderCategories: List<GenderCategory>)
    fun getGendersFlow(): Flow<List<GenderEntity>>

}