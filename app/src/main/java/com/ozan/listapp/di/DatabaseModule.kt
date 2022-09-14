package com.ozan.listapp.di

import android.content.Context
import androidx.room.Room
import com.ozan.listapp.data.db.AppDatabase
import com.ozan.listapp.data.db.dao.CartDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val APP_DATABASE_NAME = "list_app_database"

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            APP_DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideCartDao(database: AppDatabase): CartDao = database.cartDao()
}
