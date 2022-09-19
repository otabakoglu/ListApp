package com.ozan.core.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.ozan.core.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val INTERCEPTOR_LOGGING_NAME = "INTERCEPTOR_LOGGING"

    @Provides
    @Named(INTERCEPTOR_LOGGING_NAME)
    fun provideHttpLoggingInterceptor(): Interceptor? =
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor()
                .apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
        } else null

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @Named(INTERCEPTOR_LOGGING_NAME) loggingInterceptor: Interceptor?
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .apply {
                loggingInterceptor?.let {
                    addNetworkInterceptor(loggingInterceptor)
                }
            }
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        val json = Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
            explicitNulls = false
        }
        val contentType = "application/json".toMediaType()

        return Retrofit
            .Builder()
            .addConverterFactory(json.asConverterFactory(contentType))
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()
    }
}