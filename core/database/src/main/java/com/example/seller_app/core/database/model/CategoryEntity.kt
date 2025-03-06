package com.example.seller_app.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.seller_app.core.model.product.Category

@Entity("categories")
data class CategoryEntity(
    @PrimaryKey(false)
    val id: String,
    val name: String,
    val hasSizeVariation: Boolean,
    val hasColorVariation: Boolean
)

fun CategoryEntity.asExternalModel() = Category(
    id = id,
    name = name,
    hasColorVariation = hasColorVariation,
    hasSizeVariation = hasSizeVariation
)
