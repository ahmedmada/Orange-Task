package com.ahmed.data.di

import android.content.Context
import com.ahmed.data.data_source.local.LocalDataSource
import com.ahmed.data.local.LocalDataSourceImpl
import com.ahmed.data.repository.local.LocalRepositoryImpl
import com.ahmed.domain.repository.local.LocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideLocalDataSource(@ApplicationContext context: Context): LocalDataSource =
        LocalDataSourceImpl(context)

    @Provides
    @Singleton
    fun provideLocalRepository(localDataSource: LocalDataSource): LocalRepository =
        LocalRepositoryImpl(localDataSource)
}