package com.example.seller_app.features.add_product.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.seller_app.core.model.product.ProductItem
import com.example.seller_app.core.ui.component.ProductItemCard

@Composable
internal fun VariationsList(
    modifier: Modifier = Modifier,
    productItems: List<ProductItem>,
    productName: String,
    onRemove: (index: Int) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(26.dp)
    ) {
        productItems.forEachIndexed { index, variation ->
            item {

                ProductItemCard(
                    productName = productName,
                    productItem = variation,
                    onRemove = { onRemove(index) } // Future implementation
                )
            }
        }
    }
}