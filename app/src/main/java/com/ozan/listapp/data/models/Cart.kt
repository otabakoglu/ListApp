package com.ozan.listapp.data.models

import com.google.gson.annotations.SerializedName

data class Cart(

    @SerializedName("product_id")
    val productId: String,

    val name: String,
    val price: Int,
    val image: String,
    val description: String?,
)