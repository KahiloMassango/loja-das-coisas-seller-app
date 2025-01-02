package com.example.seller_app.features.product_detail

import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.seller_app.core.ui.theme.SellerappTheme
import com.example.seller_app.features.product_detail.model.ProductDetailContent

@Composable
internal fun ProductDetailScreen(
    viewModel: ProductDetailViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit
) {

    var screen by rememberSaveable { mutableStateOf(ProductDetailContent.DETAIL) }

    AnimatedContent(
        targetState = screen, label = ""
    ) { state ->
        when (state) {
            ProductDetailContent.DETAIL -> {
                DetailContent(
                    viewModel = viewModel,
                    onVariationsClick = { screen = ProductDetailContent.VARIATIONS },
                    onNavigateUp = onNavigateUp,
                )
            }
            ProductDetailContent.VARIATIONS -> {
                VariationsContent(
                    viewModel = viewModel,
                    onNavigateUp = { screen = ProductDetailContent.DETAIL }
                )
            }
        }
    }
}






@Preview
@Composable
private fun Preview() {
    SellerappTheme {
        ProductDetailScreen(
            onNavigateUp = {}
        )
    }
}