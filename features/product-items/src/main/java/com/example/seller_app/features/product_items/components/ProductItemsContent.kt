package com.example.seller_app.features.product_items.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.seller_app.core.model.product.Category
import com.example.seller_app.core.model.product.Color
import com.example.seller_app.core.model.product.ProductItem
import com.example.seller_app.core.model.product.ProductItemRequest
import com.example.seller_app.core.model.product.Size
import com.example.seller_app.core.ui.PhonePreviews
import com.example.seller_app.core.ui.component.AddVariationFAB
import com.example.seller_app.core.ui.component.AddVariationModal
import com.example.seller_app.core.ui.component.CenteredTopBar
import com.example.seller_app.core.ui.component.DeleteConfirmationDialog
import com.example.seller_app.core.ui.theme.SellerappTheme
import com.example.seller_app.features.product_items.model.ProductItemUpdate
import com.example.seller_app.features.product_items.util.mockColors
import com.example.seller_app.features.product_items.util.mockProductItems
import com.example.seller_app.features.product_items.util.mockSizes

@Composable
internal fun ProductItemsContent(
    modifier: Modifier = Modifier,
    message: String?,
    category: Category,
    productItems: List<ProductItem>,
    colorOptions: List<Color>,
    sizeOptions: List<Size>,
    addVariation: (ProductItemRequest) -> Unit,
    updateVariation: (ProductItemUpdate) -> Unit,
    deleteVariation: (String) -> Unit,
    messageShown: () -> Unit,
    onNavigateUp: () -> Unit,
) {

    var showAddVariationModal by remember { mutableStateOf(false) }
    var variationId: String? by remember { mutableStateOf(null) }
    var selectedProductItem: ProductItem? by remember { mutableStateOf(null) }
    val snackbarHostState = SnackbarHostState()

    message?.let { msg ->
        LaunchedEffect(msg) {
            snackbarHostState.showSnackbar(msg)
            messageShown()
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(snackbarHostState){
                Snackbar(
                    snackbarData = it,
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        },
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
            DeleteConfirmationDialog(
                text = "Você tem certeza que deseja excluir esta variação?",
                onConfirm = {
                    deleteVariation(variationId!!)
                    variationId = null
                },
                onDismiss = {
                    variationId = null
                }
            )
        }

    }
}

@PhonePreviews
@Composable
private fun Private() {
    SellerappTheme {
        ProductItemsContent(
            message = null,
            category = Category(id = "1", name = "Roupas", hasSizeVariation = true, hasColorVariation = true),
            productItems = mockProductItems,
            colorOptions = mockColors,
            sizeOptions = mockSizes,
            addVariation = {},
            updateVariation = {},
            deleteVariation = {},
            messageShown = {},
            onNavigateUp = {}
        )
    }
}