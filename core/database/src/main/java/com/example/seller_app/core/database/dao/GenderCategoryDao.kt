package com.example.seller_app.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.seller_app.core.database.model.GenderCategory

@Dao
interface GenderCategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertGenderCategories(genderCategories: List<GenderCategory>)

    @Query("DELETE FROM GenderCategory WHERE genderId = :genderId")
    suspend fun deleteGenderCategoryByGenderId(genderId: String)

    @Query("DELETE FROM GenderCategory")
    suspend fun deleteGenderCategories()

}