package com.example.seller_app.core.data.repositories

import com.example.seller_app.core.model.product.Product
import com.example.seller_app.core.model.product.ProductItem
import com.example.seller_app.core.model.product.ProductWithVariation
import com.example.seller_app.core.network.model.response.product.ProductWithVariationRes

interface ProductRepository {
    suspend fun getProducts(): Result<List<Product>>
    suspend fun filterProducts(gender: String?, category: String?): Result<List<Product>>
    suspend fun getProductById(productId: String): Result<ProductWithVariation>
    suspend fun addProduct(request: ProductWithVariation): Result<Product>
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
        request: List<ProductItem>
    ): Result<Unit>

    suspend fun addProductItem(
        productId: String,
        request: ProductItem
    ): Result<ProductItem>

    suspend fun updateProductItem(
        productId: String,
        request: ProductItem
    ): Result<ProductWithVariationRes>

    suspend fun deleteProductItem(productId: String, productItemId: String): Result<Unit>

}