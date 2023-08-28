package com.softeer.mycarchiving.di

import com.softeer.mycarchiving.util.NetworkInterceptor
import com.softeer.mycarchiving.util.PreferenceUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.88hey.site"

    @Provides
    @Singleton
    fun provideOkHttpClient(pref: PreferenceUtil): OkHttpClient =
        OkHttpClient.Builder().apply {
            readTimeout(5000, TimeUnit.MILLISECONDS)
            connectTimeout(5000, TimeUnit.MILLISECONDS)
            addInterceptor(NetworkInterceptor(pref))
            addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        }.build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}