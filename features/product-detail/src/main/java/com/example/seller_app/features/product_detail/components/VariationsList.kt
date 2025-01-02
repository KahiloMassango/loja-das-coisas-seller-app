package com.example.seller_app.features.product_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.seller_app.core.model.product.VariationItem

@Composable
internal fun VariationsList(
    modifier: Modifier = Modifier,
    variations: List<VariationItem>,
    productName: String,
    onVariationClick: (String) -> Unit,
    onDelete: (String) -> Unit
) {
    if(variations.isEmpty()){
        EmptyVariationsMessage()
    }else{
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(26.dp)
        ) {
            items(variations, { it.id }) { variation ->
                ProductItemCard(
                    productName = productName,
                    variationItem = variation,
                    onClick = onVariationClick,
                    onRemove = { onDelete(variation.id) }
                )
            }
        }
    }
}
