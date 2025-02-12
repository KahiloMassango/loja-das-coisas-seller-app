package com.example.seller_app.core.data

import com.example.seller_app.core.database.dao.GenderDao
import com.example.seller_app.core.database.model.GenderEntity
import com.example.seller_app.core.database.model.asExternalModel

class DefaultGenderRepository(
    private val genderDao: GenderDao
): GenderRepository {

    override fun getGenders(): List<String> {
        return genderDao.getAllGenders().map { it.asExternalModel() }
    }

    override fun addGender(gender: String) {
        genderDao.addGender(GenderEntity(name = gender))
    }
}