package com.pppp.flickrimagegallery.application.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {

    @Provides
    fun bindsContext(): Context = context
}