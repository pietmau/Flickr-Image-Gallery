package com.pppp.flickrimagegallery

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.pppp.flickrimagegallery.application.App
import com.pppp.flickrimagegallery.features.main.view.MainActivity
import com.pppp.flickrimagegallery.features.main.view.controller.Controller
import com.pppp.uscases.Event
import com.pppp.uscases.Model
import io.mockk.CapturingSlot
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {
    private val controller: Controller<Model, Event> = mockk(relaxed = true)
    private val capturingSlot: CapturingSlot<(Model) -> Unit> = slot()

    @get:Rule
    val activityRule = object : ActivityTestRule<MainActivity>(MainActivity::class.java) {
        override fun beforeActivityLaunched() {
            val app = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as App
            val component = DaggerTestAppComponent.builder().testModule(TestModule(controller)).build()
            app.appComponent = component
            component.inject(app)
        }
    }

    @Before
    fun setUp() {
        every { controller.connect(accept = capture(capturingSlot)) } answers {
            //capturingSlot.captured(Model.Complete)
        }
    }


    @Test
    fun when_foo_then_bar() {

    }
}
