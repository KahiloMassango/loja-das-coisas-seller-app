package com.example.seller_app.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.seller_app.core.model.Subcategory

@Entity("sub_categories")
data class SubcategoryEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val categoryId: String
)

fun SubcategoryEntity.asExternalModel() = Subcategory(
    name = name,
    id = id,
    categoryId = categoryId,
)
