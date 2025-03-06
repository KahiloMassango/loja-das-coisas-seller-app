package com.example.seller_app.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.seller_app.core.model.product.Size

@Entity("sizes")
data class SizeEntity(
    @PrimaryKey
    val id: String,
    val value: String,
    val categoryId: String
)

fun SizeEntity.asExternalModel() = Size(
    value = value,
    id = id,
    categoryId = categoryId
)
