package com.pppp.flickrimagegallery.setup

import com.pppp.flickrimagegallery.features.main.view.controller.Controller
import com.pppp.flickrimagegallery.application.ImageLoader
import com.pppp.flickrimagegallery.application.PicassoImageLoader
import com.pppp.uscases.Event
import com.pppp.uscases.Model
import dagger.Module
import dagger.Provides

@Module
class TestModule(private val controller: Controller<Model, Event>) {
    @Provides
    fun provideController(): Controller<Model, Event> = controller

    @Provides
    fun provideLoader(): ImageLoader =
        PicassoImageLoader
}