package com.example.seller_app.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.seller_app.core.model.Category

@Entity("categories")
data class CategoryEntity(
    @PrimaryKey
    val id: String,
    val name: String,
)

fun CategoryEntity.asExternalModel() = Category(
    id = id,
    name = name,
)
