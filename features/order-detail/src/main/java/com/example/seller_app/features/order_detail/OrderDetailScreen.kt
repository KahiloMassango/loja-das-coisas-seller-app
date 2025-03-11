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
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.seller_app.core.model.order.OrderDetail
import com.example.seller_app.core.ui.component.CenteredTopBar
import com.example.seller_app.core.ui.component.CustomButton
import com.example.seller_app.core.ui.util.toCurrency
import com.example.seller_app.features.order_detail.components.ErrorScreen
import com.example.seller_app.features.order_detail.components.LoadingScreen
import com.example.seller_app.features.order_detail.components.OrderInformation
import com.example.seller_app.features.order_detail.components.OrderItemCard
import com.example.seller_app.features.order_detail.components.OrderSummary
import com.example.seller_app.features.order_detail.model.OrderDetailUiState

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
                order = uiState.order,
                onConfirmDelivery = viewModel::confirmDelivery,
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
    order: OrderDetail,
    onConfirmDelivery: () -> Unit,
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
            SnackbarHost(snackbarHostState){
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
        Surface(
            modifier = Modifier.padding(contentPadding)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    OrderInformation(
                        modifier = Modifier.padding(bottom = 12.dp),
                        customerName = order.customerName,
                        date = order.date,
                        customerPhoneNumber = order.customerPhoneNumber,
                        delivered = order.delivered
                    )
                }

                items(order.orderItems) { orderItem ->
                    OrderItemCard(
                        orderItem = orderItem
                    )
                }

                item {
                    OrderSummary(
                        modifier = Modifier.padding(top = 12.dp),
                        deliveryAddress = order.deliveryAddressName,
                        paymentType = order.paymentType,
                        deliveryMethod = order.deliveryMethod,
                        total = order.total.toCurrency(),
                    )
                }

                if(!order.delivered){
                    item {
                        CustomButton(
                            modifier = Modifier.padding(top = 14.dp),
                            onClick = onConfirmDelivery,
                            text = "Confirmar entrega",
                        )
                    }
                }
            }
        }
    }
}


