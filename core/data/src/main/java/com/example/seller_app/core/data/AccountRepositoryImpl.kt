package com.example.seller_app.core.data

import com.example.datastore.datasource.JwtLocalDataSource
import com.example.seller_app.core.data.repositories.AccountRepository
import com.example.seller_app.core.network.datasources.StoreNetworkDatasource
import kotlinx.coroutines.flow.Flow

class AccountRepositoryImpl(
    private val storeNetworkDatasource: StoreNetworkDatasource,
    //private val userLocalDataSource: Store,
    private val jwtLocalDataSource: JwtLocalDataSource
) : AccountRepository {

    override fun isUserLoggedIn(): Flow<Boolean> {
        return jwtLocalDataSource.isLoggedIn()
    }

    override suspend fun login(identifier: String, password: String): Result<Unit> {
        return storeNetworkDatasource.login(identifier, password).mapCatching {
            jwtLocalDataSource.saveAccessToken(it.accessToken)
            jwtLocalDataSource.saveRefreshToken(it.refreshToken)
        }
    }

    override suspend fun logout(): Result<Unit> {
        return storeNetworkDatasource.logout()
    }


}