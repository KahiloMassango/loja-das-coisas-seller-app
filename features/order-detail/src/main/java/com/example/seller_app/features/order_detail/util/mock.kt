package com.example.seller_app.features.order_detail.util

import com.example.seller_app.core.model.order.OrderDetail
import com.example.seller_app.core.model.order.OrderItem

internal val orderItems = listOf(
    OrderItem(
        productName = "Camisa de Algodão com Estampa",
        image = "https://example.com/shoes.jpg",
        color = "Vermelho",
        size = "42",
        quantity = 1,
        price = 10000
    ),
    OrderItem(
        productName = "Camiseta Polo",
        image = "https://example.com/shirt.jpg",
        color = "Azul",
        size = null,
        quantity = 2,
        price = 5000
    ),
    OrderItem(
        productName = "Camiseta Polo",
        image = "https://example.com/shirt.jpg",
        color = "Amarelo",
        size = "M",
        quantity = 2,
        price = 5000
    ),
    OrderItem(
        productName = "Camiseta Polo",
        image = "https://example.com/shirt.jpg",
        color = "Azul",
        size = "M",
        quantity = 2,
        price = 5000
    )
)
internal val mockOrderDetail = OrderDetail(
    id = "12345",
    customerName = "Kahilo Pedro Massango",
    customerPhoneNumber = "+244 923 456 789",
    date = "2025-03-04",
    subTotal = 15000,
    deliveryFee = 2000,
    total = 17000,
    deliveryAddressName = "Rua 12, Bairro Tal, Luanda",
    paymentType = "Cartão de Crédito",
    deliveryMethod = "Entrega ao Domicílio",
    delivered = false,
    orderItems = orderItems
)