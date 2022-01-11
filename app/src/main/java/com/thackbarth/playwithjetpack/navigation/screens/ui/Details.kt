package com.thackbarth.playwithjetpack.navigation.screens.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.thackbarth.playwithjetpack.model.Photo

@Composable
fun DetailsScreen(navController: NavController, id: Int?) {

    val photo = MainViewModel.photoListResponse.first { it.id == id }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Row Details") },
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
            DetailsContent(photo = photo)
        }
    )
}

@ExperimentalCoilApi
@Composable
fun DetailsContent(photo: Photo) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(modifier = Modifier.padding(20.dp)) {
            Image(
                painter = rememberImagePainter(photo.url),
                contentDescription = photo.title
            )
        }
        Text(text = "Details go here: ${photo.title}")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val photo = Photo(
        1,
        1,
        "Photo 1",
        "https://www.iconsdb.com/icons/preview/red/free-badge-xxl.png",
        "https://www.iconsdb.com/icons/preview/red/free-badge-xxl.png"
    )
    DetailsContent(photo = photo)
}
