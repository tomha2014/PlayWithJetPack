package com.thackbarth.playwithjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thackbarth.playwithjetpack.model.MainViewModel
import com.thackbarth.playwithjetpack.navigation.ApplicationNavigation
//import com.thackbarth.playwithjetpack.model.PhotoNetworkEntity
import com.thackbarth.playwithjetpack.ui.theme.PlayWithJetPackTheme
import dagger.hilt.android.AndroidEntryPoint

//@ExperimentalComposeApi
//@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        MainViewModel.getMovieList()
        super.onCreate(savedInstanceState)
        setContent {
            Content()
        }
    }
}

@Composable
fun Content() {
    PlayWithJetPackTheme {
//        Scaffold(topBar = {
//            TopAppBar(backgroundColor = Color.Blue,
//                elevation = 0.dp) {
//                Text(text = "Play With Jetpack")
//            }
//        },) {
            ApplicationNavigation()
//        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PlayWithJetPackTheme {

    }
}