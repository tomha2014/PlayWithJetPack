package com.thackbarth.playwithjetpack.navigation.screens.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.thackbarth.playwithjetpack.Constants
import com.thackbarth.playwithjetpack.composables.ProductRow
import com.thackbarth.playwithjetpack.filterListForString
import com.thackbarth.playwithjetpack.getCategoryIndex
import com.thackbarth.playwithjetpack.model.MainViewModel
import com.thackbarth.playwithjetpack.model.Product
import com.thackbarth.playwithjetpack.navigation.screens.ApplicationScreens
import com.thackbarth.playwithjetpack.widgets.ButtonBar
import kotlinx.coroutines.InternalCoroutinesApi


@InternalCoroutinesApi
@Composable
fun HomeScreen(navController: NavController, viewModel: MainViewModel) {

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Shopping") },
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
fun HomeScreenContent(navController: NavController, vm: MainViewModel) {

    // NOTE TO SELF!!! vm.productList.collectAsState().value WORKS!!

    val lst = filterListForString(
        vm.filterCategory,
        Constants.EVERYTHING,
        vm.productList.collectAsState().value
    )


    Surface(color = Color.White) {
        Column() {
            vm.categoryList.value?.let {
                ButtonBar(buttons = it, selectedTabIndex = vm.selectedTabIndex,
                    buttonSelected = {
                    vm.filterCategory = it
                        vm.selectedTabIndex = getCategoryIndex(vm.categoryList.value!!,it)
                        Log.d("debug", vm.selectedTabIndex.toString())
                })
            }
            DisplayItemInRows(navController = navController, lst)
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
