package com.example.seller_app.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.seller_app.core.database.model.CategoryEntity


@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories")
    suspend fun getAllCategories(): List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCategory(category: CategoryEntity)

}