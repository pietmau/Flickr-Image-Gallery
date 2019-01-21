package com.pppp.flickrimagegallery.application.di

import com.pppp.flickrimagegallery.RepositoryModule
import com.pppp.flickrimagegallery.application.App
import com.pppp.flickrimagegallery.features.main.di.DetailActivtyModule
import com.pppp.flickrimagegallery.features.main.di.MainActivtyModule
import com.pppp.network.NetworkModule
import com.pppp.uscases.Effect
import com.pppp.uscases.di.UseCasesModule
import com.pppp.uscases.usecases.UseCase
import dagger.Component
import dagger.android.AndroidInjectionModule

interface AppComponent {
    fun inject(app: App)

    @JvmSuppressWildcards
    fun useCases(): Map<Class<out Effect>, UseCase<Effect>>
}

@Component(
    modules = [AndroidInjectionModule::class, DetailActivtyModule::class, MainActivtyModule::class, NetworkModule::class,
        RepositoryModule::class, UseCasesModule::class, AppModule::class]
)
abstract class AppComponentImpl : AppComponent
