package com.example.seller_app.features.finances.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.seller_app.features.finances.model.WithdrawStatus


@Composable
internal fun FinancesContentHeader(
    modifier: Modifier = Modifier,
    balance: Int,
    currentTab: String,
    onWithdraw: () -> Unit,
    onTabChange: (String) -> Unit,
) {
    Surface(
        modifier = modifier,
        shadowElevation = 4.dp,
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Spacer(Modifier.height(8.dp))
            BalanceCard(
                balance = balance,
                onWithdraw = onWithdraw
            )
            Spacer(Modifier.height(26.dp))
            AppTabRow(
                modifier = Modifier.fillMaxWidth(),
                currentTab = currentTab,
                tabs = WithdrawStatus.entries.map { it.description },
                onSelectTab = { onTabChange(it) }
            )
            Spacer(Modifier.height(20.dp))
        }
    }
}