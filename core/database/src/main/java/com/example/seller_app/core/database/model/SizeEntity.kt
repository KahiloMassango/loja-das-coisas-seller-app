package com.example.seller_app.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.seller_app.core.model.ProductSize

@Entity("sizes")
data class SizeEntity(
    @PrimaryKey
    val id: String,
    val value: String,
    val subcategoryId: String
)

fun SizeEntity.asExternalModel() = ProductSize(
    value = value,
    id = id,
    subcategoryId = subcategoryId
)
