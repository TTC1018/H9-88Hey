package com.softeer.mycarchiving.di

import com.softeer.data.network.ArchivingNetworkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ArchivingApiModule {

    @Provides
    @Singleton
    fun provideArchivingNetworkApi(retrofit: Retrofit): ArchivingNetworkApi =
        retrofit.create(ArchivingNetworkApi::class.java)

}