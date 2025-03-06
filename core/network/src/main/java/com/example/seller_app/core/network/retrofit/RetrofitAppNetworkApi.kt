package com.example.seller_app.core.network.retrofit

import com.example.seller_app.core.network.model.request.ProductItemDtoReq
import com.example.seller_app.core.network.model.request.ProductItemUpdateDtoReq
import com.example.seller_app.core.network.model.request.ProductUpdateDtoReq
import com.example.seller_app.core.network.model.response.GenderDtoRes
import com.example.seller_app.core.network.model.response.Response
import com.example.seller_app.core.network.model.response.product.CategoryDtoRes
import com.example.seller_app.core.network.model.response.product.ColorDtoRes
import com.example.seller_app.core.network.model.response.product.ProductDtoRes
import com.example.seller_app.core.network.model.response.product.ProductItemDtoRes
import com.example.seller_app.core.network.model.response.product.ProductWithVariationRes
import com.example.seller_app.core.network.model.response.product.SizeDtoRes
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.UUID

interface RetrofitAppNetworkApi {

    @GET("categories")
    suspend fun getCategories(): Response<List<CategoryDtoRes>>

    @GET("colors")
    suspend fun getColors(): Response<List<ColorDtoRes>>

    @GET("genders")
    suspend fun getGenders(): Response<List<GenderDtoRes>>

    @GET("sizes")
    suspend fun getSizes(): Response<List<SizeDtoRes>>

    @GET("products")
    suspend fun getProducts(): Response<List<ProductDtoRes>>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: String): Response<ProductWithVariationRes>


    @Multipart
    @POST("products")
    suspend fun addProduct(
        @Part("name") name: RequestBody,
        @Part("isAvailable") isAvailable: RequestBody,
        @Part("description") description: RequestBody,
        @Part image: MultipartBody.Part, // Image as Multipart
        @Part("categoryId") categoryId: RequestBody,
        @Part("genderId") genderId: RequestBody,
    ): Response<ProductDtoRes>


    @GET("products/filter")
    suspend fun filterProducts(
        @Query("gender") gender: String?,
        @Query("category") category: String?
    ): Response<List<ProductDtoRes>>

    @Multipart
    @PUT("products/{id}/")
    suspend fun updateProduct(
        @Path("id") id: String,
        @Part("name") name: RequestBody,
        @Part("isAvailable") isAvailable: RequestBody,
        @Part("description") description: RequestBody,
        @Part image: MultipartBody.Part?,
    ): Response<ProductDtoRes>

    @DELETE("products/{id}")
    suspend fun deleteProduct(
        @Path("id") id: String,
    )

    @Multipart
    @POST("products/{id}/variations")
    suspend fun addProductItem(
        @Path("id") productId: String,
        @Part("stockQuantity") stockQuantity: RequestBody,
        @Part("price") price: RequestBody,
        @Part image: MultipartBody.Part?,
        @Part("sizeId") sizeId: RequestBody?,
        @Part("colorId") colorId: RequestBody?
    ): Response<ProductItemDtoRes>

    @Multipart
    @PUT("products/{productId}/variations/{variationId}")
    suspend fun updateProductItem(
        @Path("productId") productId: String,
        @Path("variationId") variationId: String,
        @Part("stockQuantity") stockQuantity: RequestBody,
        @Part("price") price: RequestBody,
        @Part image: MultipartBody.Part?
    ): Response<ProductWithVariationRes>

    @DELETE("products/{productId}/variations/{variationId}")
    suspend fun deleteProductItem(
        @Path("productId") productId: String,
        @Path("variationId") variationId: String
    )
}