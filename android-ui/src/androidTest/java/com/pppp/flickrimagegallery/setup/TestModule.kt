package com.pppp.flickrimagegallery.setup

import com.pppp.flickrimagegallery.features.main.view.controller.Controller
import com.pppp.flickrimagegallery.features.main.view.customview.ClickBlocker
import com.pppp.flickrimagegallery.features.main.view.customview.ClickBlockerImpl
import com.pppp.flickrimagegallery.features.main.view.customview.ImageLoader
import com.pppp.flickrimagegallery.features.main.view.customview.PicassoImageLoader
import com.pppp.uscases.Event
import com.pppp.uscases.Model
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class TestModule(private val controller: Controller<Model, Event>) {
    @Provides
    fun provideController(): Controller<Model, Event> = controller

    @Inject
    @Provides
    fun provideClickBllocker(blocker: ClickBlockerImpl): ClickBlocker = blocker

    @Provides
    fun provideLoader(): ImageLoader = PicassoImageLoader
}