package com.example.seller_app.features.add_product.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.seller_app.core.model.product.ProductItemRequest

@Composable
internal fun ProductItemsRequestList(
    modifier: Modifier = Modifier,
    productItems: List<ProductItemRequest>,
    onRemove: (index: Int) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(26.dp)
    ) {
        productItems.forEachIndexed { index, productItemRequest ->
            item {
                ProductItemRequestCard(
                    productItemRequest = productItemRequest,
                    onRemove = { onRemove(index) } // Future implementation
                )
            }
        }
    }
}

