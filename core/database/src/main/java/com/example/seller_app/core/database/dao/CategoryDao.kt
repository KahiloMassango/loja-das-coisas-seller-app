package com.example.seller_app.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.seller_app.core.database.model.CategoryEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories")
    fun getCategoryFlow(): Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Query("DELETE FROM categories WHERE id NOT IN (:ids)")
    suspend fun deleteCategoriesNotIn(ids: List<String>)

}