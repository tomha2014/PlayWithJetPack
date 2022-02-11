package com.thackbarth.playwithjetpack

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
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
            val configuration = LocalConfiguration.current
            when (configuration.orientation){
                Configuration.ORIENTATION_PORTRAIT -> PortraitContent(viewModel)
                Configuration.ORIENTATION_LANDSCAPE -> LandscapeContent(viewModel)
            }
        }
    }
}

