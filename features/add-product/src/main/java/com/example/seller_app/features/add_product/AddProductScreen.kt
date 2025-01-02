package com.example.seller_app.features.add_product

import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.seller_app.core.ui.theme.SellerappTheme

@Composable
fun AddProductScreen(
    viewModel: AddProductViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var screen by remember { mutableIntStateOf(1) }
    val categories by viewModel.categories.collectAsStateWithLifecycle()
    val subCategories by viewModel.subCategories.collectAsStateWithLifecycle()
    val colorOptions by viewModel.colorOptions.collectAsStateWithLifecycle()
    val sizeOptions by viewModel.test.collectAsStateWithLifecycle()

    AnimatedContent(
        targetState = screen,
        label = "",
    ) {
        when(it) {
            1 -> {
                ProductInformationContent(
                    uiState = uiState,
                    onNavigateUp = onNavigateUp,
                    onNext = { screen = 2 },
                    onCategoryChange = viewModel::updateCategory,
                    onSubCategoryChange = viewModel::updateSubCategory,
                    onProductNameChange = viewModel::updateProductName,
                    onDescriptionChange = viewModel::updateDescription,
                    onImageChange = viewModel::updateImage,
                    categories = categories,
                    subCategories = subCategories,
                    onSaveProduct = {}
                )
            }
            2 -> {
                VariationsContent(
                    uiState = uiState,
                    onNavigateUp = { screen = 1 },
                    addVariation = viewModel::addVariation,
                    onRemoveVariation = viewModel::removeVariation,
                    colorOptions = colorOptions,
                    sizeOptions = sizeOptions
                )
            }
        }
    }

}




@Preview
@Composable
private fun Preview() {
    SellerappTheme {
        /*AddVariationModal(
            onDismissRequest = {}
        )*/
        AddProductScreen(
            onNavigateUp = {}
        )
    }
}