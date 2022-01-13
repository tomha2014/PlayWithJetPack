package com.thackbarth.playwithjetpack.widgets

import android.widget.HorizontalScrollView
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonBar(buttons: List<String>, buttonSelected:(result:String)->Unit) {
    ScrollableTabRow(selectedTabIndex = 0) {
        buttons.forEach{
            Text(
                text = it,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}