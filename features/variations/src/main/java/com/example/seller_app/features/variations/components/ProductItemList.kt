package com.example.seller_app.features.variations.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.seller_app.core.model.product.ProductItem

@Composable
internal fun ProductItemList(
    modifier: Modifier = Modifier,
    productItems: List<ProductItem>,
    productName: String,
    onProductItemClick: (ProductItem) -> Unit,
    onDelete: (String) -> Unit
) {
    if(productItems.isEmpty()){
        EmptyVariationsMessage()
    }else{
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(26.dp)
        ) {
            items(productItems, { it.id }) { productItem ->
                ProductItemCard(
                    productName = productName,
                    productItem = productItem,
                    onClick = { onProductItemClick(productItem) },
                    onRemove = { onDelete(productItem.id) }
                )
            }
        }
    }
}
