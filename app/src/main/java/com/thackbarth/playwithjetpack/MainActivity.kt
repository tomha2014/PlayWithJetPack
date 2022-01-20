package com.thackbarth.playwithjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.thackbarth.playwithjetpack.navigation.ApplicationNavigation
import com.thackbarth.playwithjetpack.navigation.screens.homeScreen.HomeScreenViewModel
import com.thackbarth.playwithjetpack.navigation.screens.shoppingCart.ShoppingCartViewModel
import com.thackbarth.playwithjetpack.ui.theme.PlayWithJetPackTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

//@ExperimentalComposeApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HomeScreenViewModel by viewModels()
    private val shoppingCartViewModel: ShoppingCartViewModel by viewModels()

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadAllData()

        setContent {
            Content(viewModel, shoppingCartViewModel)
        }
    }
}

@InternalCoroutinesApi
@Composable
fun Content(viewModel: HomeScreenViewModel, shoppingCartViewModel: ShoppingCartViewModel) {
    PlayWithJetPackTheme {
        ApplicationNavigation(viewModel = viewModel, shoppingCartViewModel)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PlayWithJetPackTheme {

    }
}