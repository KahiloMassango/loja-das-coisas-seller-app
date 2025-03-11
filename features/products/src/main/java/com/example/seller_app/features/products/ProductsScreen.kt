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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.seller_app.core.model.product.Product
import com.example.seller_app.core.ui.component.CenteredTopBar
import com.example.seller_app.core.ui.component.ProductCard
import com.example.seller_app.features.products.components.Header


@Composable
internal fun ProductsScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductsViewModel = hiltViewModel(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onAddNewProduct: () -> Unit,
    onProductClick: (String) -> Unit
) {
    val products by viewModel.products.collectAsStateWithLifecycle()
    val genders by viewModel.genders.collectAsStateWithLifecycle()
    val categories by viewModel.categories.collectAsStateWithLifecycle()
    val selectedGender by viewModel.currentGender.collectAsState()
    val selectedSubCategory by viewModel.currentCategory.collectAsState()
    val isRefreshing = viewModel.isRefreshing
    val message = viewModel.message

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.getProducts()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    ProductsContent(
        modifier = modifier,
        isRefreshing = isRefreshing,
        products = products,
        genders = genders,
        categories = categories,
        message = message,
        onMessageShown = viewModel::messageShown,
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
    message: String?,
    isRefreshing: Boolean,
    onMessageShown: () -> Unit,
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

    val snackbarHostState = SnackbarHostState()

    message?.let {
        LaunchedEffect(message) {
            snackbarHostState.showSnackbar(message)
            onMessageShown()
        }
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        snackbarHost = {
            SnackbarHost(snackbarHostState) {
                Snackbar(
                    snackbarData = it,
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                )
            }
        },
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
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
            state = state,
        ) {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Header(
                    selectedGender = selectedGender,
                    selectedCategory = selectedCategory,
                    genders = genders,
                    categories = categories,
                    onSelectCategory = updateCategory,
                    onSelectGender = updateGender
                )

                LazyVerticalGrid(
                    modifier = Modifier
                        .weight(1f)
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
