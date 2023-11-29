package com.ahmed.data.di

import com.ahmed.data.data_source.remote.MoviesDataSource
import com.ahmed.data.remote.data_source.MoviesRemoteDataSourceImpl
import com.ahmed.data.remote.end_points.MoviesEndPoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourcesModule {


    @Provides
    @Singleton
    fun provideMainDataSource(moviesEndPoints: MoviesEndPoints): MoviesDataSource =
        MoviesRemoteDataSourceImpl(moviesEndPoints)
}