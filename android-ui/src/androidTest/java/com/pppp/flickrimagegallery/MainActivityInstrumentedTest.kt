package com.pppp.flickrimagegallery

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.pppp.flickrimagegallery.application.App
import com.pppp.flickrimagegallery.features.main.view.MainActivity
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @get:Rule
    val activityRule = object : ActivityTestRule<MainActivity>(MainActivity::class.java) {
        override fun beforeActivityLaunched() {
            val instrumentation = InstrumentationRegistry.getInstrumentation()
            val app = instrumentation.targetContext.applicationContext as App
//            val builder = DaggerTestAppComponent.builder()
//            modelSource = DetailSource()
//            val appModule = TestAppModule(detailModelSource = modelSource, imageLoader = MockLoader)
//            val component = builder.testAppModule(appModule).build()
//            app.component = component
//            app.component.inject(app)
        }
    }




}
