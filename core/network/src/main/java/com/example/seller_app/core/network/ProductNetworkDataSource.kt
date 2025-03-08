package com.example.seller_app.core.network

import android.util.Log
import com.example.seller_app.core.network.common.extractErrorMessage
import com.example.seller_app.core.network.datasources.ProductRemoteDataSource
import com.example.seller_app.core.network.model.response.product.ProductDtoRes
import com.example.seller_app.core.network.model.response.product.ProductItemDtoRes
import com.example.seller_app.core.network.model.response.product.ProductWithVariationRes
import com.example.seller_app.core.network.retrofit.AppApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProductNetworkDataSource @Inject constructor(
    private val appNetworkApi: AppApiService
) : ProductRemoteDataSource {

    override suspend fun getProducts(): Result<List<ProductDtoRes>> {
        return withContext(Dispatchers.IO) {
            try {
                val request = appNetworkApi.getProducts()
                Result.success(request.data)
            } catch (e: HttpException) {
                Result.failure(Exception(extractErrorMessage(e)))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão de internet", null))
            }
        }
    }

    override suspend fun filterProducts(
        gender: String?,
        category: String?
    ): Result<List<ProductDtoRes>> {
        return withContext(Dispatchers.IO) {
            try {
                val request = appNetworkApi.filterProducts(gender, category)
                Result.success(request.data)
            } catch (e: HttpException) {
                Result.failure(Exception(extractErrorMessage(e)))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão de internet", null))
            }
        }
    }

    override suspend fun getProductById(productId: String): Result<ProductWithVariationRes> {
        return withContext(Dispatchers.IO) {
            try {
                val request = appNetworkApi.getProductById(productId)
                Result.success(request.data)
            } catch (e: HttpException) {
                Result.failure(Exception(extractErrorMessage(e)))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão de internet", null))
            }
        }
    }

    override suspend fun addProduct(
        name: RequestBody,
        isAvailable: RequestBody,
        description: RequestBody,
        image: MultipartBody.Part, // Image as Multipart
        categoryId: RequestBody,
        genderId: RequestBody
    ): Result<ProductDtoRes> {
        return withContext(Dispatchers.IO) {
            try {
                val response = appNetworkApi.addProduct(
                    name = name,
                    isAvailable = isAvailable,
                    description = description,
                    image = image,
                    categoryId = categoryId,
                    genderId = genderId,
                )
                Log.d("addProduct DT", "addProduct: $response")
                Result.success(response.data)
            } catch (e: HttpException) {
                Result.failure(Exception(
                   extractErrorMessage(
                        e
                    )
                ))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão de internet", null))
            }
        }
    }

    override suspend fun updateProduct(
        productId: String,
        name: RequestBody,
        isAvailable: RequestBody,
        description: RequestBody,
        image: MultipartBody.Part?,
    ): Result<ProductDtoRes> {
        return withContext(Dispatchers.IO) {
            try {
                val response = appNetworkApi.updateProduct(
                    id = productId,
                    name = name,
                    isAvailable = isAvailable,
                    description = description,
                    image = image
                )
                Result.success(response.data)
            } catch (e: HttpException) {
                Result.failure(Exception(extractErrorMessage(e)))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão de internet", null))
            }
        }
    }

    override suspend fun deleteProduct(productId: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                appNetworkApi.deleteProduct(productId)
                Result.success(Unit)
            } catch (e: HttpException) {
                Result.failure(Exception(
                    extractErrorMessage(e)
                ))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão de internet", null))
            }
        }
    }

    override suspend fun addProductItem(
        productId: String,
        stockQuantity: RequestBody,
        price: RequestBody,
        image: MultipartBody.Part?,
        sizeId: RequestBody?,
        colorId: RequestBody?
    ): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                appNetworkApi.addProductItem(
                    productId = productId,
                    stockQuantity = stockQuantity,
                    price = price,
                    image = image,
                    sizeId = sizeId,
                    colorId = colorId
                )
                Result.success(Unit)
            } catch (e: HttpException) {
                Result.failure(Exception(extractErrorMessage(e)))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão de internet", null))
            }
        }
    }

    override suspend fun updateProductItem(
        productId: String,
        variationId: String,
        stockQuantity: RequestBody,
        price: RequestBody,
        image: MultipartBody.Part?
    ): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                appNetworkApi.updateProductItem(
                    productId = productId,
                    productItemId = variationId,
                    stockQuantity = stockQuantity,
                    price = price,
                    image = image
                )
                Result.success(Unit)
            } catch (e: HttpException) {
                val response = e.message
                Result.failure(Exception("response: $response"))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão de internet", null))
            }
        }
    }

    override suspend fun deleteProductItem(productId: String, productItemId: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                appNetworkApi.deleteProductItem(productId, productItemId)
                Result.success(Unit)
            } catch (e: HttpException) {
                Result.failure(Exception(extractErrorMessage(e)))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão de internet", null))
            }
        }
    }
}