package com.example.seller_app.features.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.seller_app.core.model.product.Product
import com.example.seller_app.core.ui.component.AppDropdownMenu
import com.example.seller_app.core.ui.component.CenteredTopBar
import com.example.seller_app.core.ui.component.ProductCard
import com.example.seller_app.core.ui.component.StoreSearchTextField
import com.example.seller_app.core.ui.theme.SellerappTheme

@Composable
fun ProductsScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductsViewModel = hiltViewModel(),
    onAddNewProduct: () -> Unit,
    onProductClick: (String) -> Unit
) {
    ProductsContent(
        onAddNewProduct = onAddNewProduct,
        onProductClick = onProductClick,
        viewModel = viewModel
    )
}

@Composable
fun ProductsContent(
    viewModel: ProductsViewModel,
    onAddNewProduct: () -> Unit,
    onProductClick: (String) -> Unit
) {
    Scaffold(
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
                selectedCategory = viewModel.currentCategory.collectAsState().value,
                selectedSubCategory = viewModel.currentSubCategory.collectAsState().value,
                categories = viewModel.categories,
                subCategories = viewModel.subCategories.collectAsStateWithLifecycle().value,
                onSelectCategory = viewModel::updateCategory,
                onSelectSubCategory = viewModel::updateSubCategory
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

@Composable
internal fun ProductsHeader(
    modifier: Modifier = Modifier,
    selectedCategory: String,
    selectedSubCategory: String,
    categories: List<String>,
    subCategories: List<String>,
    onSelectCategory: (String) -> Unit,
    onSelectSubCategory: (String) -> Unit,
) {
    Surface(
        modifier = modifier,
        shadowElevation = 4.dp,
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Spacer(Modifier.height(8.dp))
            StoreSearchTextField(
                modifier = Modifier
                    .shadow(4.dp),
                query = "",
                placeholder = "Pesquisar",
                onQueryChange = {},
                onClearQuery = {},
                onSearch = {}
            )
            Spacer(Modifier.height(26.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                AppDropdownMenu(
                    modifier = Modifier.weight(1f),
                    label = "Categoria",
                    placeholder = "Selecione uma categoria",
                    selectedOption = selectedCategory,
                    options = categories,
                    onSelect = { onSelectCategory(it) }
                )
                AppDropdownMenu(
                    modifier = Modifier.weight(1f),
                    label = "Sub-categoria",
                    placeholder = "Selecione uma sub-categoria",
                    selectedOption = selectedSubCategory,
                    options = subCategories,
                    onSelect = { onSelectSubCategory(it) }
                )
            }
            Spacer(Modifier.height(20.dp))
        }
    }
}


@Preview
@Composable
private fun Preview() {
    SellerappTheme {

    }
}
