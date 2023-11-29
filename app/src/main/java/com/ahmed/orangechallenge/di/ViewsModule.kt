package com.ahmed.orangechallenge.di

import android.app.Activity
import com.ahmed.orangechallenge.util.ProgressUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object ViewsModule {

    @Provides
    @ActivityScoped
    fun provideProgressUtil(activity: Activity): ProgressUtil {
        return ProgressUtil(activity)
    }
}