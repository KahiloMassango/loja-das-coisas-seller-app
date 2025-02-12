package com.example.seller_app.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.seller_app.core.database.model.SizeEntity

@Dao
interface SizeDao {

    @Query("SELECT * FROM sizes WHERE category = :categoryName")
    suspend fun getAllSizes(categoryName: String): List<SizeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSize(size: SizeEntity)
}