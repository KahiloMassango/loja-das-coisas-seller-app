package com.example.seller_app.features.add_product.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.seller_app.core.model.product.Category
import com.example.seller_app.core.model.product.Color
import com.example.seller_app.core.model.product.ProductItemRequest
import com.example.seller_app.core.model.product.Size
import com.example.seller_app.core.ui.component.AddVariationFAB
import com.example.seller_app.core.ui.component.AddVariationModal
import com.example.seller_app.core.ui.component.CenteredTopBar

@Composable
internal fun ProductItemsContent(
    modifier: Modifier = Modifier,
    category: Category,
    productItems: List<ProductItemRequest>,
    colorOptions: List<Color>,
    sizeOptions: List<Size>,
    addVariation: (ProductItemRequest) -> Unit,
    onRemoveVariation: (index: Int) -> Unit,
    onNavigateUp: () -> Unit,
) {
    var showAddVariationModal by remember { mutableStateOf(false) }

    BackHandler {
        onNavigateUp()
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            CenteredTopBar(
                title = "Variações",
                canNavigateBack = true,
                onNavigateUp = onNavigateUp,
                elevation = 4.dp,
            )
        },
        floatingActionButton = {
            AddVariationFAB(
                onClick = { showAddVariationModal = true }
            )
        },
        containerColor = MaterialTheme.colorScheme.surface

    ) { paddingValues ->
        if (productItems.isEmpty()) {
            EmptyVariationsMessage()
        } else {
            ProductItemsRequestList(
                modifier = Modifier.padding(paddingValues),
                productItems = productItems,
                onRemove = onRemoveVariation
            )
        }
        if (showAddVariationModal) {
            AddVariationModal(
                category = category,
                colorOptions = colorOptions,
                sizeOptions = sizeOptions,
                onDismissRequest = { showAddVariationModal = false },
                onAddVariation = { addVariation(it) },
            )
        }

    }
}

