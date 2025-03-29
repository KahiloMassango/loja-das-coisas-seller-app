package com.example.seller_app.features.order_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.seller_app.core.model.order.OrderDetail
import com.example.seller_app.core.ui.PhonePreviews
import com.example.seller_app.core.ui.component.CenteredTopBar
import com.example.seller_app.core.ui.theme.SellerappTheme
import com.example.seller_app.core.ui.util.toCurrency
import com.example.seller_app.features.order_detail.components.ErrorScreen
import com.example.seller_app.features.order_detail.components.LoadingScreen
import com.example.seller_app.features.order_detail.components.OrderInformation
import com.example.seller_app.features.order_detail.components.OrderItemCard
import com.example.seller_app.features.order_detail.components.OrderSummary
import com.example.seller_app.features.order_detail.model.OrderDetailUiState
import com.example.seller_app.features.order_detail.util.mockOrderDetail

@Composable
internal fun OrderDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderDetailViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val message = viewModel.message

    when (uiState) {
        is OrderDetailUiState.Error -> ErrorScreen(
            retry = viewModel::loadOrder,
            message = uiState.message
        )

        is OrderDetailUiState.Loading -> LoadingScreen()
        is OrderDetailUiState.Success -> {
            OrderDetailContent(
                modifier = modifier,
                message = message,
                orderDetail = uiState.order,
                messageShown = viewModel::messageShown,
                onNavigateUp = onNavigateUp
            )
        }
    }


}

@Composable
internal fun OrderDetailContent(
    modifier: Modifier = Modifier,
    message: String?,
    orderDetail: OrderDetail,
    messageShown: () -> Unit,
    onNavigateUp: () -> Unit,
) {

    val snackbarHostState = SnackbarHostState()

    message?.let { msg ->
        LaunchedEffect(msg) {
            snackbarHostState.showSnackbar(msg)
            messageShown()
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(snackbarHostState) {
                Snackbar(
                    snackbarData = it,
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        },
        topBar = {
            CenteredTopBar(
                title = "Detalhes do Pedido",
                canNavigateBack = true,
                onNavigateUp = onNavigateUp,
                elevation = 5.dp
            )
        }
    ) { contentPadding ->

        LazyColumn(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                OrderInformation(
                    customerName = orderDetail.customerName,
                    date = orderDetail.date,
                    customerPhoneNumber = orderDetail.customerPhoneNumber,
                    delivered = orderDetail.delivered
                )
            }

            items(orderDetail.orderItems) { orderItem ->
                OrderItemCard(
                    orderItem = orderItem
                )
            }

            item {
                OrderSummary(
                    modifier = Modifier.padding(top = 8.dp),
                    deliveryAddress = orderDetail.deliveryAddressName,
                    paymentType = orderDetail.paymentType,
                    total = orderDetail.total.toCurrency(),
                )
            }

        }
    }
}

@PhonePreviews
@Composable
private fun Preview() {


    SellerappTheme {
        OrderDetailContent(
            message = null,
            orderDetail = mockOrderDetail,
            messageShown = {},
            onNavigateUp = {},
        )
    }
}

