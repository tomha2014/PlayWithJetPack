package com.thackbarth.playwithjetpack.navigation.screens.homeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.thackbarth.playwithjetpack.Constants
import com.thackbarth.playwithjetpack.composables.ProductRow
import com.thackbarth.playwithjetpack.filterListForString
import com.thackbarth.playwithjetpack.getCategoryIndex
import com.thackbarth.playwithjetpack.model.Product
import com.thackbarth.playwithjetpack.navigation.screens.ApplicationScreens
import com.thackbarth.playwithjetpack.widgets.ButtonBar
import kotlinx.coroutines.InternalCoroutinesApi


@InternalCoroutinesApi
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeScreenViewModel) {

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "What Not Store Front") },
            actions = {

                    if (viewModel.cartList.value.isNotEmpty()) {
                        IconButton(onClick = {
                            navController.navigate(route = ApplicationScreens.ShoppingCart.name )
                        }) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "ShoppingCart"
                            )
                        }
                    }

            },
            navigationIcon = if (navController.previousBackStackEntry != null) {
                {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            } else {
                null
            }
        )
    },
        content = {
            HomeScreenContent(navController = navController, viewModel)
        })
}

@InternalCoroutinesApi
@Composable
fun HomeScreenContent(navController: NavController, homeScreenViewModel: HomeScreenViewModel) {

    // NOTE TO SELF!!! vm.productList.collectAsState().value WORKS!!

    val lst = filterListForString(
        homeScreenViewModel.filterCategory,
        Constants.EVERYTHING,
        homeScreenViewModel.productList.collectAsState().value
    )

    Surface(color = Color.White) {
        Column() {
            ButtonBarWrapper(homeScreenViewModel = homeScreenViewModel)
            DisplayItemInRows(navController = navController, lst)
        }
    }
}

@Composable
fun ButtonBarWrapper(homeScreenViewModel: HomeScreenViewModel) {
    homeScreenViewModel.categoryList.value?.let {
        if (it.isNotEmpty()) {
            ButtonBar(buttons = it,
                selectedTabIndex = homeScreenViewModel.filterItemIndex,
                buttonSelected = {
                    homeScreenViewModel.filterCategory = it
                    homeScreenViewModel.filterItemIndex =
                        getCategoryIndex(homeScreenViewModel.categoryList.value!!, it)
                })
        }
    }
}

@InternalCoroutinesApi
@Composable
fun DisplayItemInRows(navController: NavController, lst: List<Product>) {
    LazyColumn {
        itemsIndexed(items = lst) { index, item ->
            ProductRow(item) {
                navController.navigate(route = ApplicationScreens.DetailsScreen.name + "/" + item.id)
            }
        }
    }
}
