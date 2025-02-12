package com.example.seller_app.features.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.seller_app.core.model.product.Product
import com.example.seller_app.core.ui.component.CenteredTopBar
import com.example.seller_app.core.ui.component.ProductCard
import com.example.seller_app.core.ui.theme.SellerappTheme
import com.example.seller_app.features.products.components.ProductsHeader

@Composable
fun ProductsScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductsViewModel = hiltViewModel(),
    onAddNewProduct: () -> Unit,
    onProductClick: (String) -> Unit
) {
    val genders by viewModel.genders.collectAsStateWithLifecycle()
    val categories by viewModel.categories.collectAsStateWithLifecycle()
    val selectedCategory by viewModel.currentGender.collectAsState()
    val selectedSubCategory by viewModel.currentCategory.collectAsState()

    ProductsContent(
        modifier = modifier,
        genders = genders,
        categories = categories,
        onAddNewProduct = onAddNewProduct,
        onProductClick = onProductClick,
        selectedGender = selectedCategory,
        selectedCategory = selectedSubCategory,
        updateGender = viewModel::updateCategory,
        updateCategory = viewModel::updateSubCategory,
    )
}

@Composable
fun ProductsContent(
    modifier: Modifier = Modifier,
    genders: List<String>,
    categories: List<String>,
    selectedGender: String,
    selectedCategory: String,
    updateGender: (String) -> Unit,
    updateCategory: (String) -> Unit,
    onAddNewProduct: () -> Unit,
    onProductClick: (String) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenteredTopBar(
                title = "Produtos",
                canNavigateBack = false,
                action = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                shape = RoundedCornerShape(16.dp),
                onClick = onAddNewProduct
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.surface,
        contentWindowInsets = WindowInsets.systemBars.exclude(BottomAppBarDefaults.windowInsets)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            ProductsHeader(
                selectedCategory = selectedGender,
                selectedSubCategory = selectedCategory,
                categories = genders,
                subCategories = categories,
                onSelectCategory = updateGender,
                onSelectSubCategory = updateCategory
            )
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth(),
                columns = GridCells.Adaptive(164.dp),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(22.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(5) {
                    ProductCard(
                        product = Product(
                            id = "123",
                            description = "abcdd",
                            image = "https://img4.dhresource.com/webp/m/0x0/f3/albu/km/g/01/038d6023-88d5-41eb-8110-fb7dec260c96.jpg",
                            title = "Camiseta mangas curtas"
                        ),
                        onClick = { id -> onProductClick(id) }
                    )
                }
            }
        }

    }
}

@Preview
@Composable
private fun Preview() {
    SellerappTheme {

    }
}
