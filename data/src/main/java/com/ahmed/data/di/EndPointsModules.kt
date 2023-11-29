package com.ahmed.data.di

import com.ahmed.data.remote.end_points.MoviesEndPoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object EndPointsModules {

    @Singleton
    @Provides
    fun providesMoviesEndPoints(retrofit: Retrofit): MoviesEndPoints =
        retrofit.create(MoviesEndPoints::class.java)

}