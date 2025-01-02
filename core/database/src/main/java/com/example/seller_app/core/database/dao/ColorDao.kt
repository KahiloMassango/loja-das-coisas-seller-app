package com.example.seller_app.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.seller_app.core.database.model.ColorEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ColorDao {

    @Query("SELECT * FROM colors")
    suspend fun getAllColors(): List<ColorEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addColor(color: ColorEntity)
}