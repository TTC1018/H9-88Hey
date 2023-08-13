package com.softeer.mycarchiving.di

import com.softeer.data.datasource.SelectTrimDataSource
import com.softeer.data.datasource.SelectTrimRemoteDataSource
import com.softeer.data.network.SelectTrimNetworkApi
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
    fun provideRemoteDataSource(trimNetworkApi: SelectTrimNetworkApi): SelectTrimDataSource =
        SelectTrimRemoteDataSource(trimNetworkApi)

    @Provides
    @Singleton
    fun provideSelectTrimRepository(selectTrimDataSource: SelectTrimDataSource): SelectTrimRepository =
        SelectTrimRepositoryImpl(selectTrimDataSource)

}