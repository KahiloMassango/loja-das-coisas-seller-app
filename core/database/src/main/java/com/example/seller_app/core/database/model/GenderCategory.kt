package com.example.seller_app.core.database.model

import androidx.room.Entity

@Entity(tableName = "GenderCategory", primaryKeys = ["genderId", "categoryId"])
data class GenderCategory(
    val genderId: String,
    val categoryId: String
)
