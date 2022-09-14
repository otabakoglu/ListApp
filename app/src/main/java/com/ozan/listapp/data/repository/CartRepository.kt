package com.ozan.listapp.data.repository

import com.ozan.listapp.data.models.Cart
import com.ozan.listapp.data.network.api.CartApi
import com.ozan.listapp.data.network.response.CartResponse
import com.ozan.listapp.data.network.response.Result
import com.ozan.listapp.util.extension.toResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepository @Inject constructor(
    private val cartApi: CartApi
) {
    suspend fun getCartList(): Result<CartResponse> {
        return cartApi.getCartList().toResult()
    }

    suspend fun getCartDetail(productId: String): Result<Cart> {
        return cartApi.getCartDetail(productId).toResult()
    }

}

