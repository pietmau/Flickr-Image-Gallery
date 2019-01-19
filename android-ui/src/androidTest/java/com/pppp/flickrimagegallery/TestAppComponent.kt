package com.pppp.flickrimagegallery

import com.pppp.flickrimagegallery.application.di.AppComponent
import dagger.Component
import dagger.android.AndroidInjectionModule

@Component(modules = [TestActivityModule::class, AndroidInjectionModule::class, TestModule::class])
abstract class TestAppComponent : AppComponent