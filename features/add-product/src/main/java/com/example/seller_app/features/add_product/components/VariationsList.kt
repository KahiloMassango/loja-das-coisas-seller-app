package com.example.seller_app.features.add_product.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.seller_app.features.add_product.VariationItem

@Composable
internal fun VariationsList(
    modifier: Modifier = Modifier,
    variations: List<VariationItem>,
    productName: String,
    onRemove: (index: Int) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(26.dp)
    ) {
        variations.forEachIndexed { index, variation ->
            item {
                VariationItemCard(
                    productName = productName,
                    variation = variation,
                    onRemove = { onRemove(index) } // Future implementation
                )
            }
        }
    }
}