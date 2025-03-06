package com.example.seller_app.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.seller_app.core.database.model.SizeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SizeDao {

    @Query("SELECT * FROM sizes WHERE categoryId = :categoryId")
    suspend fun getSizesByCategoryId(categoryId: String): List<SizeEntity>

    @Upsert
    suspend fun addSize(size: SizeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSizes(sizes: List<SizeEntity>)

    @Query("DELETE FROM sizes WHERE id NOT IN (:ids)")
    suspend fun deleteSizesNotIn(ids: List<String>)
}