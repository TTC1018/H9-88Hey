package com.softeer.mycarchiving.di

import com.softeer.data.datasource.ArchivingDataSource
import com.softeer.data.datasource.ArchivingRemoteDataSource
import com.softeer.data.datasource.MyArchiveDataSource
import com.softeer.data.datasource.MyArchiveRemoteDataSource
import com.softeer.data.datasource.SelectColorDataSource
import com.softeer.data.datasource.SelectColorRemoteDataSource
import com.softeer.data.datasource.SelectOptionDataSource
import com.softeer.data.datasource.SelectOptionRemoteDataSource
import com.softeer.data.datasource.SelectTrimDataSource
import com.softeer.data.datasource.SelectTrimRemoteDataSource
import com.softeer.data.datasource.SignDataSource
import com.softeer.data.datasource.SignRemoteDataSource
import com.softeer.data.network.ArchivingNetworkApi
import com.softeer.data.network.MyArchiveNetworkApi
import com.softeer.data.network.SelectColorNetworkApi
import com.softeer.data.network.SelectOptionNetworkApi
import com.softeer.data.network.SelectTrimNetworkApi
import com.softeer.data.network.SignNetworkApi
import com.softeer.data.repository.ArchivingRepositoryImpl
import com.softeer.data.repository.MyArchiveRepositoryImpl
import com.softeer.data.repository.SelectColorRepositoryImpl
import com.softeer.data.repository.SelectOptionRepositoryImpl
import com.softeer.data.repository.SelectTrimRepositoryImpl
import com.softeer.data.repository.SignRepositoryImpl
import com.softeer.domain.repository.ArchivingRepository
import com.softeer.domain.repository.MyArchiveRepository
import com.softeer.domain.repository.SelectColorRepository
import com.softeer.domain.repository.SelectOptionRepository
import com.softeer.domain.repository.SelectTrimRepository
import com.softeer.domain.repository.SignRepository
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

    @Provides
    @Singleton
    fun provideSelectOptionRemoteDataSource(selectOptionNetworkApi: SelectOptionNetworkApi): SelectOptionDataSource =
        SelectOptionRemoteDataSource(selectOptionNetworkApi)

    @Provides
    @Singleton
    fun provideSelectOptionRepository(selectOptionDataSource: SelectOptionDataSource): SelectOptionRepository =
        SelectOptionRepositoryImpl(selectOptionDataSource)

    @Provides
    @Singleton
    fun provideArchivingRemoteDataSource(archivingNetworkApi: ArchivingNetworkApi): ArchivingDataSource =
        ArchivingRemoteDataSource(archivingNetworkApi)

    @Provides
    @Singleton
    fun provideArchivingRepository(
        archivingNetworkApi: ArchivingNetworkApi,
        archivingRemoteDataSource: ArchivingDataSource
    ): ArchivingRepository =
        ArchivingRepositoryImpl(archivingNetworkApi, archivingRemoteDataSource)

    @Provides
    @Singleton
    fun provideSignRemoteDataSource(signNetworkApi: SignNetworkApi): SignDataSource =
        SignRemoteDataSource(signNetworkApi)

    @Provides
    @Singleton
    fun provideSignRepository(signDataSource: SignDataSource): SignRepository =
        SignRepositoryImpl(signDataSource)
        
    @Provides
    @Singleton
    fun provideMyArchiveRemoteDataSource(myArchiveNetworkApi: MyArchiveNetworkApi): MyArchiveDataSource =
        MyArchiveRemoteDataSource(myArchiveNetworkApi)

    @Provides
    @Singleton
    fun provideMyArchiveRepository(
        myArchiveNetworkApi: MyArchiveNetworkApi,
        myArchiveRemoteDataSource: MyArchiveDataSource
    ): MyArchiveRepository =
        MyArchiveRepositoryImpl(myArchiveNetworkApi, myArchiveRemoteDataSource)

}