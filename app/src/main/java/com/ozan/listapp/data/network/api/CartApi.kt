package com.ozan.listapp.data.network.api

import com.ozan.listapp.data.models.Cart
import com.ozan.listapp.data.network.response.CartResponse
import com.ozan.listapp.data.network.response.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CartApi {

    @GET("cart/list")
    suspend fun getCartList(): Response<CartResponse>

    @GET("cart/{productId}/detail")
    suspend fun getCartDetail(
        @Path("productId") productId: String
    ): Response<Cart>

}
