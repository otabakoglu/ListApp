package com.ozan.listapp.data.mapper

import com.ozan.listapp.data.db.model.CartModel
import com.ozan.listapp.data.models.Cart

fun CartModel.toCart() = Cart(
    productId = productId,
    name = name,
    price = price,
    image = image,
    description = description
)

fun Cart.toCartModel() = CartModel(
    productId = productId,
    name = name,
    price = price,
    image = image,
    description = description
)