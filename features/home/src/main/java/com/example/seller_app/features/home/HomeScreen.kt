package com.example.seller_app.features.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.seller_app.core.model.OrderStatus
import com.example.seller_app.core.model.order.Order
import com.example.seller_app.core.ui.component.CenteredTopBar
import com.example.seller_app.core.ui.theme.SellerappTheme
import com.example.seller_app.features.home.components.ErrorScreen
import com.example.seller_app.features.home.components.HomeHeader
import com.example.seller_app.features.home.components.LoadingScreen
import com.example.seller_app.features.home.components.OrdersContentList
import com.example.seller_app.features.home.model.HomeUiState

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    viewModel: HomeViewModel = hiltViewModel(),
    onOrderDetail: (String) -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val message = viewModel.message

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.loadOrders()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    when (uiState) {
        is HomeUiState.Loading -> LoadingScreen()
        is HomeUiState.Error -> ErrorScreen(
            message = message,
            onMessageShown = viewModel::messageShown,
            retry = viewModel::tryAgain
        )
        is HomeUiState.Success -> HomeScreenContent(
            modifier = modifier,
            message = message,
            onMessageShown = viewModel::messageShown,
            totalPendingOrders = uiState.totalPendingOrders,
            totalDeliveredOrders = uiState.totalDeliveredOrders,
            pendingOrders = uiState.pendingOrders,
            deliveredOrders = uiState.deliveredOrders,
            onOrderDetail = { id -> onOrderDetail(id)}
        )
    }
}


@Composable
private fun HomeScreenContent(
    modifier: Modifier,
    message: String?,
    onMessageShown: () -> Unit,
    totalPendingOrders: Int,
    totalDeliveredOrders: Int,
    pendingOrders: List<Order>,
    deliveredOrders: List<Order>,
    onOrderDetail: (String) -> Unit
) {
    var currentTab by remember { mutableStateOf(OrderStatus.PENDING) }
    val snackbarHostState = SnackbarHostState()

    message?.let {
        LaunchedEffect(message) {
            snackbarHostState.showSnackbar(message)
            onMessageShown()
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(snackbarHostState) {
                Snackbar(
                    snackbarData = it,
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                )
            }
        },
        topBar = {
            CenteredTopBar(
                title = "Encomendas",
                canNavigateBack = false,
                action = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        contentWindowInsets = WindowInsets.systemBars.exclude(BottomAppBarDefaults.windowInsets),
        containerColor = MaterialTheme.colorScheme.surface
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            HomeHeader(
                totalDeliveredOrders = totalDeliveredOrders,
                totalPendingOrders = totalPendingOrders,
                currentTab = currentTab,
                onSelectTab = { currentTab = it }
            )
            AnimatedContent(
                targetState = currentTab,
                modifier = Modifier.fillMaxWidth(),
            ) { state ->
                when (state) {
                    OrderStatus.PENDING -> OrdersContentList(
                        orders = pendingOrders,
                        onDetails = { id -> onOrderDetail(id) }
                    )
                    OrderStatus.DELIVERED -> OrdersContentList(
                        orders = deliveredOrders,
                        onDetails = { id -> onOrderDetail(id) }
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun Preview() {
    SellerappTheme {
        HomeScreen(
            onOrderDetail = {}
        )
    }
}
