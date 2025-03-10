package com.example.seller_app.core.network

import com.example.seller_app.core.model.FinanceStatus
import com.example.seller_app.core.network.common.extractErrorMessage
import com.example.seller_app.core.network.datasources.FinanceDataSource
import com.example.seller_app.core.network.model.response.FinanceStatusDtoRes
import com.example.seller_app.core.network.retrofit.AppApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class FinanceDataSourceImpl(
    private val appApiService: AppApiService
): FinanceDataSource {

    override suspend fun getFinanceStatus(): Result<FinanceStatusDtoRes> {
        return withContext(Dispatchers.IO){
            try {
                val response = appApiService.getFinanceStatus()
                Result.success(response.data)
            } catch (e: HttpException){
                val message = extractErrorMessage(e)
                Result.failure(Exception(message))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão de internet"))
            }
        }
    }

    override suspend fun requestWithdraw(): Result<Unit> {
        return withContext(Dispatchers.IO){
            try {
                appApiService.requestWithdraw()
                Result.success(Unit)
            } catch (e: HttpException){
                val message = extractErrorMessage(e)
                Result.failure(Exception(message))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão de internet"))
            }
        }
    }
}