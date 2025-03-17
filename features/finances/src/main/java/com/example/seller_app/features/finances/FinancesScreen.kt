package com.example.seller_app.features.finances

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.seller_app.core.model.FinanceStatus
import com.example.seller_app.core.model.WithdrawRecord
import com.example.seller_app.core.ui.component.CenteredTopBar
import com.example.seller_app.core.ui.theme.SellerappTheme
import com.example.seller_app.features.finances.components.ErrorScreen
import com.example.seller_app.features.finances.components.FinancesContentHeader
import com.example.seller_app.features.finances.components.LoadingScreen
import com.example.seller_app.features.finances.components.WithdrawRecordCard
import com.example.seller_app.features.finances.model.WithdrawStatus


@Composable
internal fun FinancesScreen(
    modifier: Modifier = Modifier,
    viewModel: FinanceViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val message = viewModel.message

    when (uiState) {
        is FinanceUiState.Loading -> LoadingScreen()
        is FinanceUiState.Error -> ErrorScreen(uiState.message, retry = viewModel::loadStatus)
        is FinanceUiState.Success -> FinancesContent(
            modifier = modifier,
            message = message,
            financeStatus = uiState.financeStatus,
            onWithdraw = viewModel::requestWithdraw,
            messageShown = viewModel::messageShown
        )
    }

}

@Composable
internal fun FinancesContent(
    modifier: Modifier = Modifier,
    message: String?,
    financeStatus: FinanceStatus,
    messageShown: () -> Unit,
    onWithdraw: () -> Unit
) {
    var currentTab by rememberSaveable { mutableStateOf(WithdrawStatus.PENDING.description) }
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
                title = "Finanças",
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
        containerColor = MaterialTheme.colorScheme.surface,
        contentWindowInsets = WindowInsets.systemBars.exclude(BottomAppBarDefaults.windowInsets)
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            item {
                FinancesContentHeader(
                    balance = financeStatus.balance,
                    currentTab = currentTab,
                    onTabChange = { currentTab = it },
                    onWithdraw = onWithdraw
                )
            }

            item {
                AnimatedContent(
                    targetState = currentTab,
                    label = "Withdraw Tab Animation"
                ) { tab ->
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        val records =
                            if (tab == WithdrawStatus.PENDING.description) financeStatus.pending
                            else financeStatus.completed

                        records.forEach { record ->
                            WithdrawRecordCard(
                                record = record
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WithdrawRecordsList(
    modifier: Modifier = Modifier,
    records: List<WithdrawRecord>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(records) { record ->
            WithdrawRecordCard(
                record = record
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    SellerappTheme {
        FinancesScreen()
    }
}
