package com.thackbarth.playwithjetpack.navigation.screens.ui

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
import com.thackbarth.playwithjetpack.composables.ProductRow
import com.thackbarth.playwithjetpack.model.MainViewModel
import com.thackbarth.playwithjetpack.navigation.screens.ApplicationScreens
import com.thackbarth.playwithjetpack.widgets.ButtonBar
import kotlinx.coroutines.InternalCoroutinesApi


@InternalCoroutinesApi
@Composable
fun HomeScreen(navController: NavController, viewModel: MainViewModel){

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Row Details") },
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
            HomeScreenContent(navController = navController, viewModel )
        })
}

@InternalCoroutinesApi
@Composable
fun HomeScreenContent(navController: NavController, vm: MainViewModel ) {

    // THIS WORKS!
    // THIS WORKS!
    // THIS WORKS!
    val lst = vm.productList.collectAsState().value.filter {
        if (vm.filterCategory == "EveryThing") {
            true
        }else {
            it.category == vm.filterCategory
        }
    }

    Surface(color = Color.White) {
        Column() {
            ButtonBar(buttons = vm.categoryList.value!!, buttonSelected = {
                vm.filterCategory = it
            })

            LazyColumn {
                itemsIndexed(items = lst) { index, item ->
                    ProductRow(item) {
                        navController.navigate(route = ApplicationScreens.DetailsScreen.name + "/" + item.id)
                    }
                }
            }
        }
    }
}