package com.softeer.mycarchiving.di

import com.softeer.data.datasource.SelectColorDataSource
import com.softeer.data.datasource.SelectColorRemoteDataSource
import com.softeer.data.datasource.SelectTrimDataSource
import com.softeer.data.datasource.SelectTrimRemoteDataSource
import com.softeer.data.network.SelectColorNetworkApi
import com.softeer.data.network.SelectTrimNetworkApi
import com.softeer.data.repository.SelectColorRepository
import com.softeer.data.repository.SelectColorRepositoryImpl
import com.softeer.data.repository.SelectTrimRepository
import com.softeer.data.repository.SelectTrimRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideSelectTrimRemoteDataSource(trimNetworkApi: SelectTrimNetworkApi): SelectTrimDataSource =
        SelectTrimRemoteDataSource(trimNetworkApi)

    @Provides
    @Singleton
    fun provideSelectTrimRepository(selectTrimDataSource: SelectTrimDataSource): SelectTrimRepository =
        SelectTrimRepositoryImpl(selectTrimDataSource)

    @Provides
    @Singleton
    fun provideSelectColorRemoteDataSource(colorNetworkApi: SelectColorNetworkApi): SelectColorDataSource =
        SelectColorRemoteDataSource(colorNetworkApi)

    @Provides
    @Singleton
    fun provideSelectColorRepository(selectColorDataSource: SelectColorDataSource): SelectColorRepository =
        SelectColorRepositoryImpl(selectColorDataSource)

}