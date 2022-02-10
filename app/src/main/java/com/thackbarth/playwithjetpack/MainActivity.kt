package com.thackbarth.playwithjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.thackbarth.playwithjetpack.navigation.screens.ApplicationScreens
import com.thackbarth.playwithjetpack.navigation.screens.details.DetailsScreen
import com.thackbarth.playwithjetpack.navigation.screens.homeScreen.AppViewModel
import com.thackbarth.playwithjetpack.navigation.screens.homeScreen.HomeScreen
import com.thackbarth.playwithjetpack.navigation.screens.shoppingCart.ShoppingCart
import com.thackbarth.playwithjetpack.navigation.screens.splash.SplashScreen
import com.thackbarth.playwithjetpack.ui.theme.PlayWithJetPackTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

//@ExperimentalComposeApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: AppViewModel by viewModels()

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadAllData()
        setContent {
            Content(viewModel)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@InternalCoroutinesApi
@Composable
fun Content(viewModel: AppViewModel) {

    val backButtonVisibleState = rememberSaveable { (mutableStateOf(true)) }
    val appBarVisibleState = rememberSaveable { (mutableStateOf(true)) }

    PlayWithJetPackTheme {
        val navController = rememberNavController()

        Surface(color = MaterialTheme.colors.background) {
            Scaffold(
                topBar = {
                    AppBar(
                        navController = navController,
                        viewModel = viewModel,
                        title = "What Not Store Front",
                        backButtonVisibleState = backButtonVisibleState,
                        appBarVisibleState = appBarVisibleState
                    )
                },
                bottomBar = {},
                drawerContent = {
                }
            ) {

                NavHost(
                    navController = navController,
                    startDestination = ApplicationScreens.SplashScreen.name
                ) {

                    composable(ApplicationScreens.SplashScreen.name) {
                        val homeViewModel = hiltViewModel<AppViewModel>()
                        LaunchedEffect(Unit) {
                            backButtonVisibleState.value = false
                            appBarVisibleState.value = false
                        }
                        SplashScreen(navController, homeViewModel)
                    }

                    composable(ApplicationScreens.HomeScreen.name) {
                        LaunchedEffect(Unit) {
                            backButtonVisibleState.value = true
                            appBarVisibleState.value = true
                        }
                        val homeViewModel = hiltViewModel<AppViewModel>()
                        HomeScreen(navController, homeViewModel)
                    }

                    composable(ApplicationScreens.ShoppingCart.name) {
                        LaunchedEffect(Unit) {
                            backButtonVisibleState.value = true
                            appBarVisibleState.value = true
                        }
                        val homeViewModel = hiltViewModel<AppViewModel>()
                        ShoppingCart(navController, homeViewModel)
                    }

                    // This is a test

                    composable(
                        ApplicationScreens.DetailsScreen.name + "/{photoId}",
                        arguments = listOf(navArgument(name = "photoId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        DetailsScreen(
                            navController,
                            backStackEntry.arguments?.getInt("photoId"),
                            viewModel
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun AppBar(navController: NavController, title: String = "Title", viewModel: AppViewModel,
           backButtonVisibleState: MutableState<Boolean>,
           appBarVisibleState: MutableState<Boolean>,
        ) {

if (appBarVisibleState.value) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = if (backButtonVisibleState.value) {
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
        },
        actions = {
            if (viewModel.cartList.collectAsState().value.isNotEmpty()) {
                IconButton(onClick = {
                    navController.navigate(route = ApplicationScreens.ShoppingCart.name)
                }) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "ShoppingCart"
                    )
                }
            }

        }
    )
}

}
