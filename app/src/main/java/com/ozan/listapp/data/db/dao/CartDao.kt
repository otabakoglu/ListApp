package com.ozan.listapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ozan.listapp.data.db.model.CartModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT * FROM CartModel")
    fun getCarts(): Flow<List<CartModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCarts(rockets: List<CartModel>)

}