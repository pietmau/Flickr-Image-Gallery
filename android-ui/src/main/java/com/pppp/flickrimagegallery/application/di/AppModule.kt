package com.pppp.flickrimagegallery.application.di

import android.content.Context
import com.pppp.flickrimagegallery.application.ImageLoader
import com.pppp.flickrimagegallery.application.PicassoImageLoader
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {

    @Provides
    fun bindsContext(): Context = context

    @Provides
    fun imageLoader(): ImageLoader = PicassoImageLoader
}
