package com.example.seller_app.features.product_detail

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.seller_app.features.product_detail.model.DetailUiState

@Composable
internal fun ProductDetailScreen(
    viewModel: ProductDetailViewModel = hiltViewModel(),
    onVariationsClick: (String) -> Unit,
    onNavigateUp: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val message = viewModel.message

    LaunchedEffect(viewModel.deleted) {
        if (viewModel.deleted) {
            onNavigateUp()
        }
    }

    when (uiState) {
        is DetailUiState.Loading -> {
            LoadingContent()
        }

        is DetailUiState.Success -> {
            DetailContent(
                uiState = uiState.product,
                message = message,
                messageShown = viewModel::messageShown,
                onVariationClick = { onVariationsClick(uiState.product.id) },
                onNavigateUp = onNavigateUp,
                updateImageUrl = viewModel::updateImageUrl,
                updateName = viewModel::updateProductName,
                updateDescription = viewModel::updateDescription,
                onSaveUpdate = viewModel::updatedProduct,
                updateIsAvailable = viewModel::updateIsAvailable,
                onDelete = {
                    viewModel.deleteProduct()
                }
            )
        }


        is DetailUiState.Error -> {
            ErrorContent(
                message = uiState.message
            )
        }

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
internal fun LoadingContent(

) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

