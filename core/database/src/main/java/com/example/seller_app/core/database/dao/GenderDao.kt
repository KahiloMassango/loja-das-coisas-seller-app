package com.example.seller_app.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.seller_app.core.database.model.GenderEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface GenderDao {

    @Query("SELECT * FROM genders")
    fun getGenderFlow(): Flow<List<GenderEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenders(genders: List<GenderEntity>)

    @Query("DELETE FROM genders WHERE id NOT IN (:ids)")
    suspend fun deleteGendersNotIn(ids: List<String>)
}