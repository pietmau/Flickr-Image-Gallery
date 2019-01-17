package com.pppp.flickrimagegallery.application.di

import com.pppp.flickrimagegallery.application.App
import com.pppp.flickrimagegallery.features.main.di.MainActivtyModule
import com.pppp.network.NetworkModule
import dagger.Component
import dagger.android.AndroidInjectionModule

@Component(modules = [AppModule::class, AndroidInjectionModule::class, MainActivtyModule::class, NetworkModule::class/*, UseCasesModule::class*/])
abstract class AppComponent {

    abstract fun inject(app: App)
}