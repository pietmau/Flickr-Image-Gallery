package com.pppp.flickrimagegallery.application

import android.app.Activity
import android.app.Application
import com.pppp.flickrimagegallery.application.di.AppComponent
import com.pppp.flickrimagegallery.application.di.AppModule
import com.pppp.flickrimagegallery.application.di.DaggerAppComponentImpl
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class App : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
        val appComponent = DaggerAppComponentImpl.builder().appModule(AppModule(this)).build()
        appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector
}
