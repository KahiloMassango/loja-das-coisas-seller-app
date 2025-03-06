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
    fun getAllColorsFlow(): Flow<List<ColorEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertColors(colors: List<ColorEntity>)

    @Query("DELETE FROM colors WHERE id NOT IN (:ids)")
    suspend fun deleteColorsNotIn(ids: List<String>)
}