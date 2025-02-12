package com.example.seller_app.core.data.model

import com.example.seller_app.core.database.model.ColorEntity
import com.example.seller_app.core.database.model.SizeEntity
import com.example.seller_app.core.model.Color
import com.example.seller_app.core.model.ProductSize


fun Color.asEntity() = ColorEntity(
    id = id,
    name = name
)

fun ProductSize.asEntity() = SizeEntity(
    id = id,
    value = value,
    category = category
)