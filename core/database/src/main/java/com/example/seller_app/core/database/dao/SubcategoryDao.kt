package com.example.seller_app.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.seller_app.core.database.model.SubcategoryEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface SubcategoryDao {

    @Query("SELECT * FROM sub_categories WHERE categoryId = :categoryId")
    suspend fun getAllSubcategories(categoryId: String): List<SubcategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSubcategory(subcategory: SubcategoryEntity)

}