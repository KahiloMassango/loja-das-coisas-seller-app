package com.example.seller_app.features.product_items

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.seller_app.features.product_items.components.ProductItemsContent
import com.example.seller_app.features.product_items.model.ProductItemsUiState

@Composable
internal fun ProductItemsScreen(
    viewmodel: ProductItemsViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit,
) {

    val uiState = viewmodel.uiState.collectAsStateWithLifecycle().value
    val colors by viewmodel.colors.collectAsStateWithLifecycle()
    val sizes by viewmodel.sizes.collectAsStateWithLifecycle()
    val message = viewmodel.message

    when(uiState) {
        is ProductItemsUiState.Loading -> LoadingScreen()
        is ProductItemsUiState.Success -> ProductItemsContent(
            message = message,
            colorOptions = colors,
            sizeOptions = sizes,
            onNavigateUp = onNavigateUp,
            category = uiState.category,
            productItems = uiState.productItems,
            addVariation = viewmodel::addProductItem,
            updateVariation = viewmodel::updateProductItem,
            deleteVariation = viewmodel::deleteVariation,
            messageShown = viewmodel::messageShown
        )
        is ProductItemsUiState.Error -> ErrorContent(uiState.message)
    }


}

@Composable
internal fun ErrorContent(
    message: String
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message
        )
    }
}


@Composable
internal fun LoadingScreen(

) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
