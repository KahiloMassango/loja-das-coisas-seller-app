package com.example.seller_app.core.network

import com.example.seller_app.core.network.common.extractErrorMessage
import com.example.seller_app.core.network.datasources.StoreNetworkDatasource
import com.example.seller_app.core.network.retrofit.AppApiService
import com.example.seller_app.core.network.retrofit.PublicApiService
import com.example.store.core.network.model.authentication.AuthenticationDtoRes
import com.example.store.core.network.model.authentication.LoginDtoReq
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class StoreNetworkDataSourceImpl(
    private val publicApiService: PublicApiService,
    private val appApiService: AppApiService
): StoreNetworkDatasource {

    override suspend fun login(identifier: String, password: String): Result<AuthenticationDtoRes> {
        return withContext(Dispatchers.IO){
            try {
                val response = publicApiService.login(LoginDtoReq(identifier, password))
                Result.success(response.data)
            } catch (e: HttpException) {
                val message = extractErrorMessage(e)
                Result.failure(Exception(message))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão de internet"))
            }
        }
    }

    override suspend fun logout(): Result<Unit> {
        return withContext(Dispatchers.IO){
            try {
                appApiService.logout()
                Result.success(Unit)
            } catch (e: HttpException) {
                val message = extractErrorMessage(e)
                Result.failure(Exception(message))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão de internet"))
            }
        }
    }

    override suspend fun getStoreDetails() {
        TODO("Not yet implemented")
    }
    
}