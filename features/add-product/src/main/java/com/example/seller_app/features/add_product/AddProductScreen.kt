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
import com.example.seller_app.features.add_product.components.ProductDetailContent
import com.example.seller_app.features.add_product.components.VariationsContent

@Composable
internal fun AddProductScreen(
    viewModel: AddProductViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var screen by remember { mutableIntStateOf(1) }
    val genders by viewModel.genders.collectAsStateWithLifecycle()
    val categories by viewModel.categories.collectAsStateWithLifecycle()
    val colorOptions by viewModel.colors.collectAsStateWithLifecycle()
    val sizeOptions by viewModel.size.collectAsStateWithLifecycle()

    AnimatedContent(
        targetState = screen,
        label = "",
    ) {
        when(it) {
            1 -> {
                ProductDetailContent(
                    uiState = uiState,
                    genders = genders,
                    categories = categories,
                    onNavigateUp = onNavigateUp,
                    onVariationsClick = { screen = 2 },
                    onGenderChange = viewModel::updateGender,
                    onCategoryChange = viewModel::updateCategory,
                    onProductNameChange = viewModel::updateName,
                    onDescriptionChange = viewModel::updateDescription,
                    onImageChange = viewModel::updateImage,
                    updateIsAvailable = viewModel::updateIsAvailable,
                    onSaveProduct = viewModel::saveProduct
                )
            }
            2 -> {
                VariationsContent(
                    category = uiState.category!!,
                    onNavigateUp = { screen = 1 },
                    addVariation = viewModel::addVariation,
                    onRemoveVariation = viewModel::removeVariation,
                    colorOptions = colorOptions,
                    sizeOptions = sizeOptions,
                    variations = uiState.variations
                )
            }
        }
    }

}
