package com.thackbarth.playwithjetpack.navigation.screens.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.thackbarth.playwithjetpack.model.MainViewModel
import com.thackbarth.playwithjetpack.model.Product
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@Composable
fun DetailsScreen(navController: NavController, id: Int?) {

    val product = id?.let { viewModel<MainViewModel>().findProductByID(it) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Product Details") },
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
            // TMH Fix This!
            // I would not be in here if there was not a product selected,
            // but bad
            DetailsContent(product = product!!)
        }
    )
}

@ExperimentalCoilApi
@Composable
fun DetailsContent(product: Product) {

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
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}
