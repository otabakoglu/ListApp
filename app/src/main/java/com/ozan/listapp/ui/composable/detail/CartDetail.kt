package com.ozan.listapp.ui.composable.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ozan.listapp.data.models.Cart
import com.ozan.listapp.ui.theme.Typography

@Composable
fun CartDetailItem(
    cart: Cart
) {
    Column {
        AsyncImage(
            model = cart.image,
            contentDescription = "image",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = cart.name,
                style = Typography.subtitle1
            )

            Text(
                text = "${cart.price} ₺",
                style = Typography.body1
            )

            cart.description?.let {
                Text(
                    text = it,
                    style = Typography.body1
                )
            }
        }

    }
}