package com.example.seller_app.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.seller_app.core.database.model.SizeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SizeDao {

    @Query("SELECT * FROM sizes WHERE subcategoryId = :subCategory")
    suspend fun getAllSizes(subCategory: String): List<SizeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSize(size: SizeEntity)
}