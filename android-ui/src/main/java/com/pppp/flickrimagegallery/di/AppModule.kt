package com.pppp.flickrimagegallery.di

import android.content.Context
import com.pppp.flickrimagegallery.application.imageloader.ImageLoader
import com.pppp.flickrimagegallery.application.imageloader.PicassoImageLoader
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {

    @Provides
    fun bindsContext(): Context = context

    @Provides
    fun imageLoader(): ImageLoader =
        PicassoImageLoader
}
