package com.example.seller_app.core.database

import com.example.seller_app.core.database.dao.GenderDao
import com.example.seller_app.core.database.datasources.GenderLocalDataSource
import com.example.seller_app.core.database.model.GenderEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GenderLocalDataSourceImpl(
    private val genderDao: GenderDao
): GenderLocalDataSource {

    override fun getGendersFlow(): Flow<List<GenderEntity>> {
        return genderDao.getGenderFlow()
    }

    override suspend fun upsertGenders(genders: List<GenderEntity>) {
       withContext(Dispatchers.IO) {
           genderDao.insertGenders(genders)
       }
    }

    override suspend fun deleteGendersNotIn(genders: List<String>) {
        withContext(Dispatchers.IO) {
            genderDao.deleteGendersNotIn(genders)
        }
    }

}