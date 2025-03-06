package com.example.seller_app.features.variations

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
import com.example.seller_app.features.variations.components.VariationsContent
import com.example.seller_app.features.variations.model.VariationsUiState

@Composable
internal fun VariationsScreen(
    viewmodel: VariationsViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit,
) {

    val uiState = viewmodel.uiState.collectAsStateWithLifecycle().value
    val colors by viewmodel.colors.collectAsStateWithLifecycle()
    val sizes by viewmodel.sizes.collectAsStateWithLifecycle()

    when(uiState) {
        is VariationsUiState.Loading -> LoadingScreen()
        is VariationsUiState.Success -> VariationsContent(
            colorOptions = colors,
            sizeOptions = sizes,
            onNavigateUp = onNavigateUp,
            productName = uiState.productName,
            category = uiState.category,
            productItems = uiState.productItems,
            addVariation = viewmodel::addProductItem,
            updateVariation = viewmodel::updateProductItem,
            deleteVariation = viewmodel::deleteVariation
        )
        is VariationsUiState.Error -> ErrorContent(uiState.message)
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
