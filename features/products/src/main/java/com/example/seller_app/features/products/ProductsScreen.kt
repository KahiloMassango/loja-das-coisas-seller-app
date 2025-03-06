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
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.seller_app.core.model.product.Product
import com.example.seller_app.core.ui.component.CenteredTopBar
import com.example.seller_app.core.ui.component.ProductCard
import com.example.seller_app.features.products.components.Header


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProductsScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductsViewModel = hiltViewModel(),
    onAddNewProduct: () -> Unit,
    onProductClick: (String) -> Unit
) {
    val products by viewModel.products.collectAsState()
    val genders by viewModel.genders.collectAsStateWithLifecycle()
    val categories by viewModel.categories.collectAsStateWithLifecycle()
    val selectedGender by viewModel.currentGender.collectAsState()
    val selectedSubCategory by viewModel.currentCategory.collectAsState()
    val isRefreshing = viewModel.isRefreshing

    ProductsContent(
        modifier = modifier,
        isRefreshing = isRefreshing,
        products = products,
        genders = genders,
        categories = categories,
        onRefresh = viewModel::refresh,
        onAddNewProduct = onAddNewProduct,
        onProductClick = onProductClick,
        selectedGender = selectedGender,
        selectedCategory = selectedSubCategory,
        updateGender = viewModel::updateGender,
        updateCategory = viewModel::updateCategory,
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProductsContent(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean,
    products: List<Product>,
    genders: List<String>,
    onRefresh: () -> Unit,
    categories: List<String>,
    selectedGender: String?,
    selectedCategory: String?,
    updateGender: (String?) -> Unit,
    updateCategory: (String?) -> Unit,
    onAddNewProduct: () -> Unit,
    onProductClick: (String) -> Unit
) {

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
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
        val state = rememberPullToRefreshState()
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Header(
                selectedGender = selectedGender,
                selectedCategory = selectedCategory,
                genders = genders,
                categories = categories,
                onSelectCategory = updateCategory,
                onSelectGender = updateGender
            )
            PullToRefreshBox(
                isRefreshing = isRefreshing,
                onRefresh = onRefresh,
                state = state,
            ) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth(),
                    columns = GridCells.Adaptive(164.dp),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(22.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(products) { product ->
                        ProductCard(
                            product = product,
                            onClick = { id -> onProductClick(id) }
                        )
                    }
                }
            }

        }
    }
}
