package com.example.seller_app.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.seller_app.core.model.Category

@Entity("categories")
data class CategoryEntity(
    @PrimaryKey(true)
    val id: Int = 0,
    val name: String,
)

fun CategoryEntity.asExternalModel() = Category(
    name = name,
    id = id,
)
