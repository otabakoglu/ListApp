package com.ozan.listapp.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cart(

    @SerialName("product_id")
    val productId: String,

    val name: String,
    val price: Int,
    val image: String,

    val description: String?,
)