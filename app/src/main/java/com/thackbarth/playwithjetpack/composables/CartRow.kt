package com.thackbarth.playwithjetpack.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.thackbarth.playwithjetpack.model.CartItem
import com.thackbarth.playwithjetpack.model.Product


@ExperimentalCoilApi
@Composable
fun CartRow(cartItem: CartItem, product: Product, onItemClick: (Product) -> Unit = {}) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
//                onItemClick(product)
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
//        backgroundColor = Color.Black.copy(alpha = 0.2f),
        elevation = 6.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(60.dp),
                shape = RectangleShape,
                elevation = 4.dp
            ) {
                Image(
                    painter = rememberImagePainter(product.image),
                    contentDescription = cartItem.productID.toString()
                )
            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.h5
                )

                Text(
                    text = "Quantity : ${cartItem.quantity}",
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}