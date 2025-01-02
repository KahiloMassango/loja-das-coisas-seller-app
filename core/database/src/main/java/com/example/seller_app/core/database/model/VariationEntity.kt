package com.example.seller_app.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.seller_app.core.model.Variation

@Entity("variations")
data class VariationEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val subcategoryId: Int
)

fun VariationEntity.asExternalModel() = Variation(
    name = name
)
