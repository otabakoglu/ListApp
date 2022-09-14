package com.ozan.listapp.data.repository

import com.ozan.listapp.data.db.dao.CartDao
import com.ozan.listapp.data.mapper.toCart
import com.ozan.listapp.data.mapper.toCartModel
import com.ozan.listapp.data.models.Cart
import com.ozan.listapp.data.network.api.CartApi
import com.ozan.listapp.data.network.response.Result
import com.ozan.listapp.util.extension.resultOf
import com.ozan.listapp.util.extension.toResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepositoryImpl @Inject constructor(
    private val cartApi: CartApi,
    private val cartDao: CartDao
) : CartRepository {

    override suspend fun getCartList(): Flow<Result<List<Cart>>> =
        cartDao
            .getCarts()
            .map { cartModel ->
                cartModel.map { it.toCart() }
            }
            .onEach { cartList ->
                if (cartList.isEmpty()) {
                    refreshCartList()
                }
            }
            .map {
                resultOf { it }
            }

    override suspend fun refreshCartList() {
        cartApi
            .getCartList()
            .body()
            ?.products
            ?.map { cart ->
                cart.toCartModel()
            }
            ?.also {
                cartDao.saveCarts(it)
            }
    }

    override suspend fun getCartDetail(productId: String): Result<Cart> =
        cartApi
            .getCartDetail(productId)
            .toResult()

}

