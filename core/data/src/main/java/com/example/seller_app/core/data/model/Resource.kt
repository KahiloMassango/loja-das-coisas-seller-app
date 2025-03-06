package com.example.seller_app.core.data.model

import com.example.seller_app.core.database.model.CategoryEntity
import com.example.seller_app.core.database.model.ColorEntity
import com.example.seller_app.core.database.model.GenderEntity
import com.example.seller_app.core.database.model.SizeEntity
import com.example.seller_app.core.model.product.Product
import com.example.seller_app.core.model.product.ProductItem
import com.example.seller_app.core.model.product.ProductWithVariation
import com.example.seller_app.core.network.model.request.ProductDtoReq
import com.example.seller_app.core.network.model.request.ProductItemDtoReq
import com.example.seller_app.core.network.model.request.ProductItemUpdateDtoReq
import com.example.seller_app.core.network.model.request.ProductUpdateDtoReq
import com.example.seller_app.core.network.model.response.GenderDtoRes
import com.example.seller_app.core.network.model.response.product.CategoryDtoRes
import com.example.seller_app.core.network.model.response.product.ColorDtoRes
import com.example.seller_app.core.network.model.response.product.SizeDtoRes



fun ColorDtoRes.asEntity() = ColorEntity(
    id = id,
    name = name
)

fun SizeDtoRes.asEntity() = SizeEntity(
    id = id,
    value = value,
    categoryId = categoryId
)

fun GenderDtoRes.asEntity() = GenderEntity(
    id = id,
    name = name
)


fun CategoryDtoRes.asEntity() = CategoryEntity(
    id = id,
    name =  name,
    hasColorVariation = hasColorVariation,
    hasSizeVariation = hasSizeVariation
)

fun ProductWithVariation.toDtoReq() = ProductDtoReq(
    name = name,
    isAvailable = isAvailable,
    imageUrl = imageUrl,
    categoryId = category.id,
    genderId = gender.id,
    description = description,
    productItems = productItems.map { it.toDtoReq() },
)

fun Product.toUpdateDto() = ProductUpdateDtoReq(
    name = name,
    isAvailable = isAvailable,
    description = description,
    imageUrl = imageUrl
)

fun ProductItem.toDtoReq() = ProductItemDtoReq(
    sizeId = size?.id,
    colorId = color?.id,
    price = price,
    stockQuantity = stockQuantity,
    imageUrl = imageUrl
)

fun ProductItem.toUpdateDto() = ProductItemUpdateDtoReq(
    price = price,
    stockQuantity = stockQuantity,
    imageUrl = imageUrl
)
