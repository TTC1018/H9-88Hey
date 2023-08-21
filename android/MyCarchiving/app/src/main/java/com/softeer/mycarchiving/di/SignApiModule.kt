package com.softeer.mycarchiving.di

import com.softeer.data.network.SignNetworkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SignApiModule {

    @Provides
    @Singleton
    fun provideSignNetworkApi(retrofit: Retrofit): SignNetworkApi =
        retrofit.create(SignNetworkApi::class.java)
}