package com.example.seller_app.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.seller_app.core.database.model.GenderEntity


@Dao
interface GenderDao {

    @Query("SELECT * FROM genders")
    fun getAllGenders(): List<GenderEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addGender(category: GenderEntity)
}