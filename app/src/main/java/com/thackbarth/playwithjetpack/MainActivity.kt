package com.thackbarth.playwithjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.thackbarth.playwithjetpack.model.MainViewModel
//import com.thackbarth.playwithjetpack.model.PhotoNetworkEntity
import com.thackbarth.playwithjetpack.network.PhotoApi
import com.thackbarth.playwithjetpack.ui.theme.PlayWithJetPackTheme

class MainActivity : ComponentActivity() {

    val vm = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        vm.getMovieList()

        super.onCreate(savedInstanceState)
        setContent {
            PlayWithJetPackTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    LazyColumn {
                        itemsIndexed(items = vm.photoListResponse) { index, item ->
                            Greeting(item.title)
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PlayWithJetPackTheme {
        Greeting("Android")
    }
}