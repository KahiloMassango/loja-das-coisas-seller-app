package com.example.seller_app.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.seller_app.core.model.Gender

@Entity("genders")
data class GenderEntity(
    @PrimaryKey
    val id: String,
    val name: String,
)

fun GenderEntity.asExternalModel(): Gender = Gender(id, name)