package com.example.seller_app.core.data

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.seller_app.core.data.model.getFileFromUri
import com.example.seller_app.core.data.model.toMultipart
import com.example.seller_app.core.data.repositories.ProductRepository
import com.example.seller_app.core.model.product.Product
import com.example.seller_app.core.model.product.ProductItemRequest
import com.example.seller_app.core.model.product.ProductRequest
import com.example.seller_app.core.model.product.ProductWithVariation
import com.example.seller_app.core.network.datasources.ProductRemoteDataSource
import com.example.seller_app.core.network.model.response.product.asExternalModel

class ProductRepositoryImpl(
    private val remoteDataSource: ProductRemoteDataSource,
    private val context: Context,
) : ProductRepository {

    override suspend fun getProducts(): Result<List<Product>> {
        return remoteDataSource.getProducts().mapCatching { products ->
            products.map { it.asExternalModel() }
        }
    }

    override suspend fun filterProducts(gender: String?, category: String?): Result<List<Product>> {
        return remoteDataSource.filterProducts(gender, category).mapCatching { products ->
            products.map { it.asExternalModel() }
        }
    }

    override suspend fun getProductById(productId: String): Result<ProductWithVariation> {
        return remoteDataSource.getProductById(productId).mapCatching { it.asExternalModel() }
    }

    override suspend fun addProduct(request: ProductRequest): Result<Product> {
        return remoteDataSource.addProduct(
            name = request.name.toMultipart(),
            isAvailable = request.isAvailable.toMultipart(),
            description = request.description.toMultipart(),
            image = getFileFromUri(Uri.parse(request.imageUrl), context).toMultipart(),
            categoryId = request.category.id.toMultipart(),
            genderId = request.gender.id.toMultipart(),
        ).mapCatching {
            addProductItemList(
                it.id,
                request.productItems
            )
            it.asExternalModel()
        }
    }

    override suspend fun updateProduct(
        productId: String,
        name: String,
        isAvailable: Boolean,
        description: String,
        image: String?
    ): Result<Product> {
        return remoteDataSource.updateProduct(
            productId = productId,
            name = name.toMultipart(),
            isAvailable = isAvailable.toMultipart(),
            description = description.toMultipart(),
            image = image?.let { getFileFromUri(Uri.parse(it), context).toMultipart() },
        ).mapCatching {
            it.asExternalModel()
        }

    }

    override suspend fun deleteProduct(productId: String): Result<Unit> {
        return remoteDataSource.deleteProduct(productId)
    }

    override suspend fun addProductItem(
        productId: String,
        request: ProductItemRequest
    ): Result<Unit>{
        return remoteDataSource.addProductItem(
            productId = productId,
            stockQuantity = request.stockQuantity.toMultipart(),
            price = request.price.toMultipart(),
            image = getFileFromUri(Uri.parse(request.imageUrl), context).toMultipart(),
            sizeId = request.size?.id?.toMultipart(),
            colorId = request.color?.id?.toMultipart(),
        )
    }

    override suspend fun addProductItemList(
        productId: String,
        request: List<ProductItemRequest>
    ): Result<Unit> {
        request.forEach {
            addProductItem(productId, it)
        }
        return Result.success(Unit)
    }

    override suspend fun updateProductItem(
        productId: String,
        productItemId: String,
        stockQuantity: Int,
        price: Int,
        image: String?
    ): Result<Unit> {
        return remoteDataSource.updateProductItem(
            productId = productId,
            variationId = productItemId,
            stockQuantity = stockQuantity.toMultipart(),
            price = price.toMultipart(),
            image = image?.let { getFileFromUri(Uri.parse(it), context).toMultipart() },
        )
    }

    override suspend fun deleteProductItem(productId: String, productItemId: String): Result<Unit> {
        return remoteDataSource.deleteProductItem(productId, productItemId)
    }
}