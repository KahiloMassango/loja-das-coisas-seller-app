package com.example.seller_app.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.seller_app.core.model.product.Category

@Entity("categories")
data class CategoryEntity(
    @PrimaryKey(false)
    val id: String,
    @ColumnInfo(name = "name")
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
