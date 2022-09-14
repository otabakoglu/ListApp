package com.ozan.listapp.data.network.response

import com.ozan.listapp.data.models.Cart

data class CartResponse(
    val products: List<Cart?>?
)