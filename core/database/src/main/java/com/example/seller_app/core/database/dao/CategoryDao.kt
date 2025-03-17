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


    @Query(
        "SELECT c.id, c.name, c.hasColorVariation, c.hasSizeVariation " +
        "FROM categories c " +
        "INNER JOIN GenderCategory gc ON c.id = gc.categoryId " +
        "WHERE gc.genderId = :genderId"
    )
    fun getCategoriesByGenderFlow(genderId: String): Flow<List<CategoryEntity>>

    @Query(
        "SELECT c.id, c.name, c.hasSizeVariation, c.hasColorVariation " +
        "FROM categories c " +
        "INNER JOIN GenderCategory gc ON c.id = gc.categoryId " +
        "INNER JOIN genders g ON g.id = gc.genderId " +
        "WHERE g.name = :genderName"
    )
    fun getCategoriesByGenderName(genderName: String): Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Query("DELETE FROM categories WHERE id NOT IN (:ids)")
    suspend fun deleteCategoriesNotIn(ids: List<String>)

}