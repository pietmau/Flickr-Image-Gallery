package com.pppp.flickrimagegallery.setup

import com.pppp.flickrimagegallery.features.main.view.controller.Controller
import com.pppp.flickrimagegallery.application.imageloader.ImageLoader
import com.pppp.flickrimagegallery.application.imageloader.PicassoImageLoader
import com.pppp.uscases.main.events.Event
import com.pppp.uscases.main.events.Model
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