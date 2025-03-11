package com.example.seller_app.core.data.util

import kotlinx.coroutines.flow.Flow

interface NetworkMonitor {

    val hasInternetConnectionFlow: Flow<Boolean>

    fun hasNetworkConnection(): Boolean
}