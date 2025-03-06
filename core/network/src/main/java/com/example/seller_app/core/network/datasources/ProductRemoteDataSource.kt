package com.example.seller_app.core.network.datasources

import com.example.seller_app.core.network.model.request.ProductUpdateDtoReq
import com.example.seller_app.core.network.model.response.product.ProductDtoRes
import com.example.seller_app.core.network.model.response.product.ProductItemDtoRes
import com.example.seller_app.core.network.model.response.product.ProductWithVariationRes
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Part

interface ProductRemoteDataSource {
    suspend fun getProducts(): Result<List<ProductDtoRes>>
    suspend fun filterProducts(gender: String?, category: String?): Result<List<ProductDtoRes>>
    suspend fun getProductById(productId: String): Result<ProductWithVariationRes>

    suspend fun addProduct(
        name: RequestBody,
        isAvailable: RequestBody,
        description: RequestBody,
        image: MultipartBody.Part,
        categoryId: RequestBody,
        genderId: RequestBody
    ): Result<ProductDtoRes>

    suspend fun deleteProduct(productId: String): Result<Unit>

    suspend fun updateProduct(
        productId: String,
        name: RequestBody,
        isAvailable: RequestBody,
        description: RequestBody,
        image: MultipartBody.Part?,
    ): Result<ProductDtoRes>

    suspend fun addProductItem(
        productId: String,
        stockQuantity: RequestBody,
        price: RequestBody,
        image: MultipartBody.Part?,
        sizeId: RequestBody?,
        colorId: RequestBody?
    ): Result<ProductItemDtoRes>

    suspend fun updateProductItem(
        productId: String,
        variationId: String,
        stockQuantity: RequestBody,
        price: RequestBody,
        image: MultipartBody.Part?
    ): Result<ProductWithVariationRes>

    suspend fun deleteProductItem(productId: String, productItemId: String): Result<Unit>

}