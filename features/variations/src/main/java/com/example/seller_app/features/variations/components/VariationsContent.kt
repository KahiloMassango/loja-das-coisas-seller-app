package com.example.seller_app.features.variations.components

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
import com.example.seller_app.core.model.product.ProductItem
import com.example.seller_app.core.model.product.Size
import com.example.seller_app.core.ui.component.AddVariationFAB
import com.example.seller_app.core.ui.component.AddVariationModal
import com.example.seller_app.core.ui.component.CenteredTopBar
import com.example.seller_app.core.ui.component.DeleteDialog

@Composable
internal fun VariationsContent(
    modifier: Modifier = Modifier,
    productName: String,
    category: Category,
    productItems: List<ProductItem>,
    colorOptions: List<Color>,
    sizeOptions: List<Size>,
    addVariation: (ProductItem) -> Unit,
    updateVariation: (ProductItem) -> Unit,
    deleteVariation: (String) -> Unit,
    onNavigateUp: () -> Unit,
) {

    var showAddVariationModal by remember { mutableStateOf(false) }
    var variationId: String? by remember { mutableStateOf(null) }
    var selectedProductItem: ProductItem? by remember { mutableStateOf(null) }

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
        ProductItemList(
            modifier = Modifier.padding(paddingValues),
            productItems = productItems,
            productName = productName,
            onProductItemClick = { selectedProductItem = it },
            onDelete = {
                variationId = it
            }
        )
        if (showAddVariationModal) {
            AddVariationModal(
                category = category,
                colorOptions = colorOptions,
                sizeOptions = sizeOptions,
                onDismissRequest = { showAddVariationModal = false },
                onAddVariation = {
                    addVariation(it)
                    showAddVariationModal = false
                }
            )
        }
        if (selectedProductItem != null) {
            UpdateVariationModal(
                category = category,
                productItem = selectedProductItem!!,
                onDismissRequest = { selectedProductItem = null },
                onUpdate = { update ->
                    updateVariation(update)
                    selectedProductItem = null
                }
            )
        }
        if (variationId != null) {
            DeleteDialog(
                text = "Você tem certeza que deseja excluir esta variação?",
                onConfirm = {
                    deleteVariation(variationId!!)
                    variationId = null
                },
                onDismissRequest = {
                    variationId = null
                }
            )
        }

    }
}