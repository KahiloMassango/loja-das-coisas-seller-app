package com.example.seller_app.core.data

import com.example.seller_app.core.model.Category

interface GenderRepository {
    fun getGenders(): List<String>
    fun addGender(gender: String)

}