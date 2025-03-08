package com.example.seller_app.core.data.repositories

import com.example.seller_app.core.model.product.Product
import com.example.seller_app.core.model.product.ProductItem
import com.example.seller_app.core.model.product.ProductItemRequest
import com.example.seller_app.core.model.product.ProductRequest
import com.example.seller_app.core.model.product.ProductWithVariation
import com.example.seller_app.core.network.model.response.product.ProductWithVariationRes

interface ProductRepository {
    suspend fun getProducts(): Result<List<Product>>
    suspend fun filterProducts(gender: String?, category: String?): Result<List<Product>>
    suspend fun getProductById(productId: String): Result<ProductWithVariation>
    suspend fun addProduct(request: ProductRequest): Result<Product>
    suspend fun deleteProduct(productId: String): Result<Unit>


    suspend fun updateProduct(
        productId: String,
        name: String,
        isAvailable: Boolean,
        description: String,
        image: String?
    ): Result<Product>

    suspend fun addProductItemList(
        productId: String,
        request: List<ProductItemRequest>
    ): Result<Unit>

    suspend fun addProductItem(
        productId: String,
        request: ProductItemRequest
    ): Result<Unit>

    suspend fun updateProductItem(
        productId: String,
        productItemId: String,
        stockQuantity: Int,
        price: Int,
        image: String?
    ): Result<Unit>

    suspend fun deleteProductItem(productId: String, productItemId: String): Result<Unit>

}