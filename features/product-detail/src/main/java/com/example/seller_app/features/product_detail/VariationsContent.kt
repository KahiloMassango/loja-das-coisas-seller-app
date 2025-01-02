package com.example.seller_app.features.product_detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.seller_app.core.ui.component.CenteredTopBar
import com.example.seller_app.features.product_detail.components.AddVariationFAB
import com.example.seller_app.features.product_detail.components.AddVariationModal
import com.example.seller_app.features.product_detail.components.DeleteVariationModal
import com.example.seller_app.features.product_detail.components.UpdateVariationModal
import com.example.seller_app.features.product_detail.components.VariationsList

@Composable
internal fun VariationsContent(
    modifier: Modifier = Modifier,
    viewModel: ProductDetailViewModel,
    onNavigateUp: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val selectedVariation = viewModel.selectedVariation.collectAsState().value
    val colorOptions by viewModel.colorOptions.collectAsState()
    val sizeOptions by viewModel.sizeOptions.collectAsState()

    var showAddVariationModal by remember { mutableStateOf(false) }
    var variationId: String? by remember { mutableStateOf(null) }

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
        VariationsList(
            modifier = Modifier.padding(paddingValues),
            variations = uiState.variations,
            productName = uiState.productName,
            onVariationClick = { viewModel.selectVariation(it) },
            onDelete = {
                variationId = it
            }
        )
        if (showAddVariationModal) {
            AddVariationModal(
                subCategory = uiState.subCategory,
                colorOptions = colorOptions,
                sizeOptions = sizeOptions,
                onDismissRequest = { showAddVariationModal = false },
                onAddVariation = { viewModel.addVariation(it) }
            )
        }
        if (selectedVariation != null) {
            UpdateVariationModal(
                subCategory = uiState.subCategory,
                variationItem = selectedVariation,
                onDismissRequest = { viewModel.clearSelectedVariation() },
                onUpdateVariation = { viewModel.updateVariation(it) }
            )
        }
        if (variationId != null) {
            DeleteVariationModal(
                onConfirm = {
                    viewModel.deleteVariation(variationId!!)
                    variationId = null
                },
                onDismissRequest = {
                    variationId = null
                }
            )
        }

    }
}

