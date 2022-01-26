package com.thackbarth.playwithjetpack.navigation.screens.details

//  ====
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.thackbarth.playwithjetpack.model.Product
import com.thackbarth.playwithjetpack.navigation.screens.homeScreen.AppViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalAnimationApi
@InternalCoroutinesApi
@Composable
fun DetailsScreen(navController: NavController, id: Int?, vm: AppViewModel) {

    val product = id?.let { vm.findProductByID(it, vm.productList.value) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Product Details") },

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
            if (product != null) {
                DetailsContent(product = product, vm)
            }
        }
    )
}

@ExperimentalAnimationApi
@ExperimentalCoilApi
@Composable
fun DetailsContent(product: Product, mainViewModel: AppViewModel) {


    var productFoundInCart by remember {
        mutableStateOf(mainViewModel.productInCart(product = product, mainViewModel.cartList.value))
    }

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


        Column() {
            AnimatedVisibility(
                visible = !productFoundInCart,
                enter = fadeIn(
                    // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
                    initialAlpha = 0.4f
                ),
                exit = fadeOut(
                    // Overwrites the default animation with tween
                    animationSpec = tween(durationMillis = 250)
                )
            ) {

                    Button(
                        onClick = {
                            mainViewModel.addProductToShoppingCart(product.id)
                            productFoundInCart = true
                        },
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


    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}
