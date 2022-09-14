package com.ozan.listapp.ui.composable.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ozan.listapp.R
import com.ozan.listapp.data.models.Cart
import com.ozan.listapp.ui.theme.Typography


@Composable
fun CartListContent(
    cartList: List<Cart?>,
    onCartClick: (String) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(
            cartList
        ) { item ->
            item?.let {
                CartItem(
                    cart = it,
                    onCartClick = { onCartClick(item.productId) }
                )
            }
        }
    }
}

@Composable
fun CartItem(
    cart: Cart,
    onCartClick: () -> Unit
) {

    Column(modifier = Modifier.padding(16.dp)) {

        Surface(
            elevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onCartClick() },
            shape = RoundedCornerShape(8.dp),
            color = colorResource(id = R.color.white),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(8.dp)
            ) {
                AsyncImage(
                    model = cart.image,
                    contentScale = ContentScale.Fit,
                    contentDescription = "image",
                    modifier = Modifier
                        .width(100.dp)
                        .aspectRatio(1f)
                )

                Text(
                    text = cart.name,
                    style = Typography.subtitle1,
                    color = colorResource(id = R.color.black)
                )

                Text(
                    text = "${cart.price} ₺",
                    style = Typography.body1,
                    color = colorResource(id = R.color.black)
                )
            }
        }

    }
}