package com.example.seller_app.core.data.model

import com.example.seller_app.core.database.model.CategoryEntity
import com.example.seller_app.core.database.model.ColorEntity
import com.example.seller_app.core.database.model.SizeEntity
import com.example.seller_app.core.database.model.SubcategoryEntity
import com.example.seller_app.core.model.Category
import com.example.seller_app.core.model.ProductColor
import com.example.seller_app.core.model.ProductSize
import com.example.seller_app.core.model.Subcategory

fun Category.asEntity() = CategoryEntity(
    id = id,
    name = name,
)

fun Subcategory.asEntity() = SubcategoryEntity(
    id = id,
    name = name,
    categoryId = categoryId
)

fun ProductColor.asEntity() = ColorEntity(
    id = id,
    value = value
)

fun ProductSize.asEntity() = SizeEntity(
    id = id,
    value = value,
    subcategoryId = subcategoryId
)