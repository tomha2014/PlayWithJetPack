package com.thackbarth.playwithjetpack.navigation.screens.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.thackbarth.playwithjetpack.composables.CartRow
import com.thackbarth.playwithjetpack.model.CartItem
import com.thackbarth.playwithjetpack.model.MainViewModel
import com.thackbarth.playwithjetpack.navigation.screens.ApplicationScreens
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@Composable
fun ShoppingCart(navController: NavController, mainViewModel: MainViewModel) {


    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Shopping Cart") },

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
            ShoppingCartScreenContent( navController, mainViewModel)
        })

}

@InternalCoroutinesApi
@Composable
fun ShoppingCartScreenContent(navController: NavController, viewModel: MainViewModel) {

    val lst = viewModel.shoppingCart.value?.toList()

    Surface(color = Color.White) {
        Column() {
            if (lst != null) {
                DisplayShoppingCartRow(navController = navController, lst)
            }
        }
    }
}

@InternalCoroutinesApi
@Composable
fun DisplayShoppingCartRow(navController: NavController, lst: List<CartItem>) {
    LazyColumn {
        itemsIndexed(items = lst) { index, item ->
            CartRow(item) {
//                navController.navigate(route = ApplicationScreens.DetailsScreen.name + "/" + item.id)
            }
        }
    }
}
