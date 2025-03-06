package com.example.seller_app.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.seller_app.core.model.product.Color

@Entity("colors")
data class ColorEntity(
    @PrimaryKey
    val id: String,
    val name: String,
)

fun ColorEntity.asExternalModel() = Color(
    name = name,
    id = id
)
