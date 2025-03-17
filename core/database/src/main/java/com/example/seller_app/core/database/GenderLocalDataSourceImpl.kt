package com.example.seller_app.core.database

import com.example.seller_app.core.database.dao.GenderCategoryDao
import com.example.seller_app.core.database.dao.GenderDao
import com.example.seller_app.core.database.datasources.GenderLocalDataSource
import com.example.seller_app.core.database.model.GenderCategory
import com.example.seller_app.core.database.model.GenderEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GenderLocalDataSourceImpl(
    private val genderDao: GenderDao,
    private val genderCategoryDao: GenderCategoryDao
): GenderLocalDataSource {

    override fun getGendersFlow(): Flow<List<GenderEntity>> {
        return genderDao.getGenderFlow()
    }

    override suspend fun upsertGenders(genders: List<GenderEntity>) {
       withContext(Dispatchers.IO) {
           val newGenderIds = genders.map { it.id }
           genderDao.insertGenders(genders)
           genderDao.deleteGendersNotIn(newGenderIds)
       }
    }


    override suspend fun saveGenderCategories(genderCategories: List<GenderCategory>) {
        withContext(Dispatchers.IO) {
            genderCategoryDao.deleteGenderCategories()
             genderCategoryDao.upsertGenderCategories(genderCategories)
        }
    }


}