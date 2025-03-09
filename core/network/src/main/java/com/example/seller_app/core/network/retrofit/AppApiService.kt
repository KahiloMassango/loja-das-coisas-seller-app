package com.example.seller_app.core.network.retrofit

import com.example.seller_app.core.network.model.response.GenderDtoRes
import com.example.seller_app.core.network.model.Response
import com.example.seller_app.core.network.model.response.order.OrderDetailDtoRes
import com.example.seller_app.core.network.model.response.order.OrdersDtoRes
import com.example.seller_app.core.network.model.response.product.CategoryDtoRes
import com.example.seller_app.core.network.model.response.product.ColorDtoRes
import com.example.seller_app.core.network.model.response.product.ProductDtoRes
import com.example.seller_app.core.network.model.response.product.ProductItemDtoRes
import com.example.seller_app.core.network.model.response.product.ProductWithVariationRes
import com.example.seller_app.core.network.model.response.product.SizeDtoRes
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface AppApiService {

    @GET("categories")
    suspend fun getCategories(): Response<List<CategoryDtoRes>>

    @GET("colors")
    suspend fun getColors(): Response<List<ColorDtoRes>>

    @GET("genders")
    suspend fun getGenders(): Response<List<GenderDtoRes>>

    @GET("sizes")
    suspend fun getSizes(): Response<List<SizeDtoRes>>

    @GET("auth/logout")
    suspend fun logout()

    // Orders
    @GET("stores/orders")
    suspend fun getOrder(): Response<OrdersDtoRes>


    @GET("stores/orders/{id}")
    suspend fun getOrderById(@Path("id") id: String): Response<OrderDetailDtoRes>

    @PATCH("stores/orders/{id}/delivered")
    suspend fun confirmDeliveredOrder(@Path("id") id: String)

    // Products

    @GET("stores/products/{id}")
    suspend fun getProductById(@Path("id") id: String): Response<ProductWithVariationRes>

    @GET("stores/products")
    suspend fun getProducts(): Response<List<ProductDtoRes>>

    @Multipart
    @POST("stores/products")
    suspend fun addProduct(
        @Part("name") name: RequestBody,
        @Part("isAvailable") isAvailable: RequestBody,
        @Part("description") description: RequestBody,
        @Part image: MultipartBody.Part, // Image as Multipart
        @Part("categoryId") categoryId: RequestBody,
        @Part("genderId") genderId: RequestBody,
    ): Response<ProductDtoRes>


    @GET("stores/products/filter")
    suspend fun filterProducts(
        @Query("gender") gender: String?,
        @Query("category") category: String?
    ): Response<List<ProductDtoRes>>

    @Multipart
    @PUT("stores/products/{id}")
    suspend fun updateProduct(
        @Path("id") id: String,
        @Part("name") name: RequestBody,
        @Part("isAvailable") isAvailable: RequestBody,
        @Part("description") description: RequestBody,
        @Part image: MultipartBody.Part?,
    ): Response<ProductDtoRes>

    @DELETE("stores/products/{id}")
    suspend fun deleteProduct(
        @Path("id") id: String,
    )

    // Product Items

    @Multipart
    @POST("stores/products/{productId}/product-items")
    suspend fun addProductItem(
        @Path("productId") productId: String,
        @Part("stockQuantity") stockQuantity: RequestBody,
        @Part("price") price: RequestBody,
        @Part image: MultipartBody.Part?,
        @Part("sizeId") sizeId: RequestBody?,
        @Part("colorId") colorId: RequestBody?
    )

    @Multipart
    @PUT("stores/products/{productId}/product-items/{productItemId}")
    suspend fun updateProductItem(
        @Path("productId") productId: String,
        @Path("productItemId") productItemId: String,
        @Part("stockQuantity") stockQuantity: RequestBody,
        @Part("price") price: RequestBody,
        @Part image: MultipartBody.Part?
    )

    @DELETE("stores/products/{productId}/product-items/{productItemId}")
    suspend fun deleteProductItem(
        @Path("productId") productId: String,
        @Path("productItemId") productItemId: String
    )

}