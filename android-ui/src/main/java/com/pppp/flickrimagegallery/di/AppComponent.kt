package com.pppp.flickrimagegallery.di

import com.pppp.flickrimagegallery.application.App
import com.pppp.flickrimagegallery.features.main.di.DetailActivtyModule
import com.pppp.flickrimagegallery.features.main.di.MainActivtyModule
import com.pppp.network.NetworkModule
import com.pppp.uscases.UseCase
import com.pppp.uscases.di.UseCasesModule
import com.pppp.uscases.main.events.Effect
import com.pppp.uscases.main.events.Event
import dagger.Component
import dagger.android.AndroidInjectionModule

interface AppComponent {
    fun inject(app: App)
}

@Component(
    modules = [AndroidInjectionModule::class, DetailActivtyModule::class, MainActivtyModule::class, NetworkModule::class,
        RepositoryModule::class, UseCasesModule::class, AppModule::class]
)
abstract class AppComponentImpl : AppComponent {
    @JvmSuppressWildcards
    abstract fun useCases(): Map<Class<out Effect>, UseCase<Effect, Event>>
}
