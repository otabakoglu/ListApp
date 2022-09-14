package com.ozan.listapp.data.repository

import com.ozan.listapp.data.models.Cart
import com.ozan.listapp.data.network.response.Result
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun getCartList(): Flow<Result<List<Cart>>>
    suspend fun refreshCartList(): Flow<Error>
    suspend fun getCartDetail(productId: String): Result<Cart>
}

