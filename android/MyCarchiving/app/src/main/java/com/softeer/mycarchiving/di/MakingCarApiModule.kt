package com.softeer.mycarchiving.di

import com.softeer.data.network.SelectTrimNetworkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MakingCarApiModule {

    @Provides
    @Singleton
    fun provideSelectTrimNetworkApi(retrofit: Retrofit): SelectTrimNetworkApi =
        retrofit.create(SelectTrimNetworkApi::class.java)

}