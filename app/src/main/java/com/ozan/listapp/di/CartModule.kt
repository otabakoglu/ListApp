package com.ozan.listapp.di

import com.ozan.listapp.data.network.api.CartApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CartModule {

    @Provides
    @Singleton
    fun provideCartApi(
        retrofit: Retrofit
    ): CartApi = retrofit.create(CartApi::class.java)

}