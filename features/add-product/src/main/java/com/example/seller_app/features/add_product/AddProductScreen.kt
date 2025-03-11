package com.example.seller_app.features.add_product

import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.seller_app.features.add_product.components.ProductDetailContent
import com.example.seller_app.features.add_product.components.ProductItemsContent

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
    val sizeOptions by viewModel.sizes.collectAsStateWithLifecycle()
    val message = viewModel.message

    LaunchedEffect(viewModel.productAdded) {
        if (viewModel.productAdded) {
            onNavigateUp()
        }
    }

    AnimatedContent(
        targetState = screen,
        label = "",
    ) {
        when(it) {
            1 -> {
                ProductDetailContent(
                    uiState = uiState,
                    message = message,
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
                    onSaveProduct = viewModel::saveProduct,
                    messageShown = viewModel::messageShown
                )
            }
            2 -> {
                ProductItemsContent(
                    category = uiState.category!!,
                    onNavigateUp = { screen = 1 },
                    addVariation = viewModel::addProductItem,
                    onRemoveVariation = viewModel::removeVariation,
                    colorOptions = colorOptions,
                    sizeOptions = sizeOptions,
                    productItems =  uiState.productItems
                )
            }
        }
    }

}
