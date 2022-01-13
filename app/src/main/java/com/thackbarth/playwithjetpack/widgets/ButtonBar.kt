package com.thackbarth.playwithjetpack.widgets

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ButtonBar(buttons: List<String>, buttonSelected:(result:String)->Unit) {
    Row() {
        ScrollableTabRow(selectedTabIndex = 0, modifier = Modifier.fillMaxWidth()) {
            for (category in buttons) {
                Text(

                    text = category,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier.padding(8.dp).clickable {
                                                                buttonSelected(category)
                    },

                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val names = listOf("tom", "robin", "katie")

    ButtonBar(names) {
        Log.d("test", it)
    }
}