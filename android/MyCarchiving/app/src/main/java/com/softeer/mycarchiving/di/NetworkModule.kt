package com.softeer.mycarchiving.di

import com.softeer.mycarchiving.constant.AUTHORIZATION
import com.softeer.mycarchiving.constant.CONTENT_TYPE
import com.softeer.mycarchiving.util.PreferenceUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "http://api.88hey.site:8080"

    @Provides
    @Singleton
    fun provideOkHttpClient(pref: PreferenceUtil): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            .addInterceptor(Interceptor {
                val accessToken = pref.accessToken
                val newRequest = it.request().newBuilder()
                    .addHeader(AUTHORIZATION, "Bearer $accessToken")
                    .addHeader(CONTENT_TYPE, "application/json")
                    .build()
                it.proceed(newRequest)
            })
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}