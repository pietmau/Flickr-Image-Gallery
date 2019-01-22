package com.pppp.flickrimagegallery.setup

import com.pppp.flickrimagegallery.di.AppComponent
import dagger.Component
import dagger.android.AndroidInjectionModule

@Component(modules = [TestActivityModule::class, AndroidInjectionModule::class, TestModule::class])
abstract class TestAppComponent : AppComponent