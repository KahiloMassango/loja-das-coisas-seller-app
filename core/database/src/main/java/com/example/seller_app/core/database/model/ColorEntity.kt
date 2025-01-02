package com.example.seller_app.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.seller_app.core.model.ProductColor

@Entity("colors")
data class ColorEntity(
    @PrimaryKey
    val id: String,
    val value: String,
)

fun ColorEntity.asExternalModel() = ProductColor(
    value = value,
    id = id
)
