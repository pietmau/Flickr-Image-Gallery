package com.pppp.flickrimagegallery.application.di

import com.pppp.flickrimagegallery.RepositoryModule
import com.pppp.flickrimagegallery.application.App
import com.pppp.flickrimagegallery.features.main.di.MainActivtyModule
import com.pppp.network.NetworkModule
import com.pppp.uscases.di.UseCasesModule
import dagger.Component
import dagger.android.AndroidInjectionModule

interface AppComponent {
    fun inject(app: App)
}

@Component(
    modules = [AndroidInjectionModule::class, MainActivtyModule::class, NetworkModule::class,
        RepositoryModule::class, UseCasesModule::class, AppModule::class]
)
abstract class AppComponentImpl : AppComponent