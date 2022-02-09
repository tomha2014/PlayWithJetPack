package com.thackbarth.playwithjetpack.widgets

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ButtonBar(
    buttons: List<String>,
    selectedTabIndex: Int = 0,
    buttonSelected: (result: String) -> Unit
) {

        Surface(
            elevation = 8.dp,
            color = Color.White
        ) {
            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = Color.White
            ) {

                for (category in buttons) {
                    Chip(name = category, buttonSelected)
                }
            }
        }

}

@Composable
fun Chip(name: String = "chip", buttonSelected: (result: String) -> Unit) {
    Surface(
        modifier = Modifier.padding( 6.dp),
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        color = Color.Red

    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(
                text = name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2,
                color = Color.White,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable {
                        buttonSelected(name)
                    }
                    .fillMaxWidth()

            )
        }
    }

}

@Preview(showBackground = false)
@Composable
fun DefaultPreview_chip() {
    val names = listOf("tom", "robin", "katie")

    Chip(buttonSelected = {})

}

@Preview(showBackground = false)
@Composable
fun DefaultPreview() {
    val names = listOf("tom", "robin", "katie", "Todd", "Cindy", "Audrey")
    ButtonBar(names) {
//        Log.d("test", it)
    }
}