package com.example.seller_app.core.network.datasources

import com.example.seller_app.core.network.model.response.GenderDtoRes

interface GenderRemoteDataSource {

    suspend fun fetchGenders(): Result<List<GenderDtoRes>>

}