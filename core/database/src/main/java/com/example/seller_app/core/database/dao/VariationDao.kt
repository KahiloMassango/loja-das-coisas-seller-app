package com.example.seller_app.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.seller_app.core.database.model.VariationEntity

@Dao
interface VariationDao {

    @Query("SELECT * FROM variations WHERE subcategoryId = :subcategoryId")
    suspend fun getVariationsBySubCategory(subcategoryId: String): List<VariationEntity>

    @Upsert
    suspend fun upsertAll(variations: List<VariationEntity>)

}