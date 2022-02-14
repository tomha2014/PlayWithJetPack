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
fun HomeScreen(navController: NavController, viewModel: AppViewModel) {
    HomeScreenContent(navController = navController, viewModel)
}

@InternalCoroutinesApi
@Composable
fun HomeScreenContent(navController: NavController, appViewModel: AppViewModel) {
    // NOTE TO SELF!!! vm.productList.collectAsState().value WORKS!!
    val lst = filterListForString(
        appViewModel.filterCategory,
        Constants.EVERYTHING,
        appViewModel.productList.collectAsState().value
    )

    Surface(color = Color.White) {
        Column() {
            if( !appViewModel.landscapeMode) {
                ButtonBarWrapper(appViewModel = appViewModel)
            }
            DisplayItemInRows(navController = navController, lst)
        }
    }
}

@Composable
fun ButtonBarWrapper(appViewModel: AppViewModel) {
    appViewModel.categoryList.value?.let {
        if (it.isNotEmpty()) {
            ButtonBar(buttons = it,
                selectedTabIndex = appViewModel.filterItemIndex,
                buttonSelected = { buttonName ->
                    appViewModel.filterCategory = buttonName
                    appViewModel.filterItemIndex =
                        getCategoryIndex(appViewModel.categoryList.value!!, buttonName)
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
