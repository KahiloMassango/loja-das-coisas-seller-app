package com.example.seller_app.features.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.seller_app.core.model.order.Order
import com.example.seller_app.core.ui.PhonePreviews
import com.example.seller_app.core.ui.component.CustomOutlinedButton
import com.example.seller_app.core.ui.theme.SellerappTheme
import com.example.seller_app.core.ui.util.toCurrency

@Composable
internal fun OrderCard(
    modifier: Modifier = Modifier,
    order: Order,
    onDetails: (String) -> Unit
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(3.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = order.customerName,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = order.date,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Itens: ${order.totalItems}",
                    style = MaterialTheme.typography.bodySmall,
                )
                Text(
                    text = "Total: " + order.total.toCurrency(),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                CustomOutlinedButton(
                    onClick = { onDetails(order.id) },
                    text = "Detalhes"
                )
                Text(
                    text = if(order.isPending) "Pendente" else "Entregue",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium,
                    color = if(order.isPending) MaterialTheme.colorScheme.primary else
                        MaterialTheme.colorScheme.scrim
                )
            }
        }
    }
}



@PhonePreviews
@Preview
@Composable
private fun Preview() {
    SellerappTheme {
        val orderExample = Order(
            id = "fdsfsdfsd",
            customerName = "Kahio Pedro Massango",
            date = "2025-03-01",
            isPending = true,
            total = 1073741834,
            totalItems = 4
        )
        OrderCard(
            order = orderExample,
            onDetails = {}
        )
    }
}

