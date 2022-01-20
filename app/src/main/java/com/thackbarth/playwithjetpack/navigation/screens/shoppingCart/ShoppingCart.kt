package com.thackbarth.playwithjetpack.navigation.screens.shoppingCart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.thackbarth.playwithjetpack.composables.CartRow
import com.thackbarth.playwithjetpack.model.CartItem
import com.thackbarth.playwithjetpack.model.Product
import com.thackbarth.playwithjetpack.navigation.screens.homeScreen.HomeScreenViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@Composable
fun ShoppingCart(navController: NavController,
                 shoppingCartViewModel:HomeScreenViewModel = hiltViewModel()) {

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
            ShoppingCartScreenContent( navController, shoppingCartViewModel)
        })

}

@InternalCoroutinesApi
@Composable
fun ShoppingCartScreenContent(navController: NavController,
                              viewModel: HomeScreenViewModel) {


    val productList = viewModel.productList.collectAsState().value
    val shoppingCartItemList = viewModel.cartList.collectAsState().value


    Surface(color = Color.White) {
        Column() {
            if (shoppingCartItemList != null) {
                DisplayShoppingCartRow(navController = navController, shoppingCartItemList, productList)
            }
        }
    }
}

@InternalCoroutinesApi
@Composable
fun DisplayShoppingCartRow(
    navController: NavController,
    shoppingCartItemList: List<CartItem>,
    productList: List<Product>
) {

    LazyColumn {
        itemsIndexed(items = shoppingCartItemList) { index, item ->
            val product = productList.first(){it.id == item.productID}
            CartRow(item, product = product) {}
        }
    }
}
