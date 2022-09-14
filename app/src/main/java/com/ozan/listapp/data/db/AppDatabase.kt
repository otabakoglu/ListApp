package com.ozan.listapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ozan.listapp.data.db.dao.CartDao
import com.ozan.listapp.data.db.model.CartModel

private const val DATABASE_VERSION = 1

@Database(
    entities = [CartModel::class],
    version = DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}
