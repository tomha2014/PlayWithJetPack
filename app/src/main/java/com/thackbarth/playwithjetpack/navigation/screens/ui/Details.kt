package com.thackbarth.playwithjetpack.navigation.screens.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.thackbarth.playwithjetpack.model.MainViewModel
import com.thackbarth.playwithjetpack.model.Product
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@Composable
fun DetailsScreen(navController: NavController, id: Int?, vm: MainViewModel ) {

    val product = id?.let { vm.findProductByID(it) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Product Details") },
                actions = {
                    if (vm.shoppingCart.value!= null) {
                        if (vm.shoppingCart.value!!.size > 0) {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Default.ShoppingCart,
                                    contentDescription = "ShoppingCart"
                                )
                            }
                        }
                    }
                },
                navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                }
            )
        },
        content = {
            // TMH Fix This!
            // I would not be in here if there was not a product selected,
            // but bad
            if (product != null) {
                DetailsContent(product = product, vm)
            }
        }
    )
}

@ExperimentalCoilApi
@Composable
fun DetailsContent(product: Product, mainViewModel: MainViewModel) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(modifier = Modifier.padding(20.dp)) {
            Image(
                painter = rememberImagePainter(product.image),
                contentDescription = product.title
            )
        }
        Text(text = "Details go here: ${product.title}")
        Spacer(Modifier.size(20.dp))
        Button(
            onClick = { mainViewModel.addProductToShoppingList(product.id) },
            // Uses ButtonDefaults.ContentPadding by default
            contentPadding = PaddingValues(
                start = 20.dp,
                top = 12.dp,
                end = 20.dp,
                bottom = 12.dp
            )
        ) {
            // Inner content including an icon and a text label
            Icon(
                Icons.Filled.ShoppingCart,
                contentDescription = "Favorite",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Add to cart")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}
