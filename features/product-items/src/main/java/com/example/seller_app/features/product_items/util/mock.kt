package com.example.seller_app.features.product_items.util

import com.example.seller_app.core.model.product.Color
import com.example.seller_app.core.model.product.ProductItem
import com.example.seller_app.core.model.product.Size

val mockProductItems = listOf(
    ProductItem(
        id = "1",
        color = Color(name = "Vermelho", id = ""),
        imageUrl = "https://via.placeholder.com/150",
        price = 5000,
        size = Size("", value = "M", categoryId = ""),
        stockQuantity = 10
    ),
    ProductItem(
        id = "2",
        color = Color(name = "Vermelho", id = ""),
        imageUrl = "https://via.placeholder.com/150",
        price = 6000,
        size = null,
        stockQuantity = 5
    ),
    ProductItem(
        id = "3",
        color = Color(name = "Vermelho", id = ""),
        imageUrl = "https://via.placeholder.com/150",
        price = 6000,
        size = Size("", value = "M", categoryId = ""),
        stockQuantity = 5
    )
)

val mockColors = listOf(
    Color(name = "Vermelho", id = ""),
    Color(name = "Azul", id = ""),
    Color(name = "Verde", id = "")
)

val mockSizes = listOf(
    Size(value = "P", id = "", categoryId = ""),
    Size(value = "M", id = "", categoryId = ""),
    Size(value = "G", id = "", categoryId = "")
)
