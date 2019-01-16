package com.pppp.flickrimagegallery.application.di

import com.pppp.flickrimagegallery.application.App
import com.pppp.flickrimagegallery.features.main.di.MainActivtyModule
import dagger.Component
import dagger.android.AndroidInjectionModule

@Component(modules = [AppModule::class, AndroidInjectionModule::class, MainActivtyModule::class/*, UseCasesModule::class*/])
abstract class AppComponent {

    abstract fun inject(app: App)
}