package com.thackbarth.playwithjetpack

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thackbarth.playwithjetpack.navigation.ApplicationNavigation
import com.thackbarth.playwithjetpack.navigation.screens.homeScreen.HomeScreenViewModel
import com.thackbarth.playwithjetpack.ui.theme.PlayWithJetPackTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

//@ExperimentalComposeApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HomeScreenViewModel by viewModels()

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadAllData()

        setContent {
            Content(viewModel)
        }
    }
}

@InternalCoroutinesApi
@Composable
fun Content(viewModel: HomeScreenViewModel) {
    PlayWithJetPackTheme {
        ApplicationNavigation(viewModel = viewModel)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PlayWithJetPackTheme {

    }
}