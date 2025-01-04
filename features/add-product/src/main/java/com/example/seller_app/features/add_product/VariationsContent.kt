package com.example.seller_app.features.add_product

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
import com.example.seller_app.core.ui.component.CenteredTopBar
import com.example.seller_app.features.add_product.components.AddVariationFAB
import com.example.seller_app.features.add_product.components.AddVariationModal
import com.example.seller_app.features.add_product.components.EmptyVariationsMessage
import com.example.seller_app.features.add_product.components.VariationsList

@Composable
internal fun VariationsContent(
    modifier: Modifier = Modifier,
    uiState: ProductUiState,
    colorOptions: List<String>,
    sizeOptions: List<String>,
    addVariation: (VariationItem) -> Unit,
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
        if (uiState.variations.isEmpty()) {
            EmptyVariationsMessage()
        } else {
            VariationsList(
                modifier = Modifier.padding(paddingValues),
                variations = uiState.variations,
                productName = uiState.productName,
                onRemove = onRemoveVariation
            )
        }
        if (showAddVariationModal) {
            AddVariationModal(
                subCategory = uiState.subCategory ?: "",
                colorOptions = colorOptions,
                sizeOptions = sizeOptions,
                onDismissRequest = { showAddVariationModal = false },
                onAddVariation = { addVariation(it) },
            )
        }

    }
}

