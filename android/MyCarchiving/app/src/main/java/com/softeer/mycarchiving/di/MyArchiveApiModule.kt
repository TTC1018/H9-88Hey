package com.softeer.mycarchiving.di

import com.softeer.data.network.MyArchiveNetworkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyArchiveApiModule {

    @Provides
    @Singleton
    fun provideMyArchiveNetworkApi(retrofit: Retrofit): MyArchiveNetworkApi =
        retrofit.create(MyArchiveNetworkApi::class.java)
}