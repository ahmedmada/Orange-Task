package com.ahmed.data.di

import com.ahmed.data.data_source.remote.MoviesDataSource
import com.ahmed.data.repository.remote.MoviesRepositoryImpl
import com.ahmed.domain.repository.remote.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {


    @Singleton
    @Provides
    fun providesMoviesRepository(moviesDataSource: MoviesDataSource): MoviesRepository =
        MoviesRepositoryImpl(moviesDataSource)
}