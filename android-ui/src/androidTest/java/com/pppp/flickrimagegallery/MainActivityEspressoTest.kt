package com.pppp.flickrimagegallery

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.pppp.entites.FlickrImage
import com.pppp.flickrimagegallery.application.App
import com.pppp.flickrimagegallery.features.main.view.MainActivity
import com.pppp.flickrimagegallery.features.main.view.controller.Controller
import com.pppp.flickrimagegallery.setup.DaggerTestAppComponent
import com.pppp.flickrimagegallery.setup.TestModule
import com.pppp.uscases.Event
import com.pppp.uscases.Model
import io.mockk.*
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private const val TEXT = "TEXT"

@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {
    private val controller: Controller<Model, Event> = mockk(relaxed = true)
    private val capturingSlot: CapturingSlot<(Model) -> Unit> = slot()
    private val throwable: Throwable = mockk()
    private val error =
        Model.Error(throwable)// Mockk unable to mock data class constructor property?
    private val image: FlickrImage = mockk()
    private val complete = Model.Complete(listOf(image))

    @get:Rule
    val activityRule = object : ActivityTestRule<MainActivity>(MainActivity::class.java) {
        override fun beforeActivityLaunched() {
            val app =
                InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as App
            val testModule = TestModule(controller)
            val component = DaggerTestAppComponent.builder().testModule(testModule).build()
            app.appComponent = component
            component.inject(app)
            every { controller.connect(accept = capture(capturingSlot)) } just Runs
        }
    }

    @Before
    fun setUp() {
        every { image.imageUrl } returns TEXT
        every { image.title } returns TEXT
        every { throwable.localizedMessage } returns TEXT
    }

    @Test
    internal fun when_starts_progress_shows() {
        // WHEN
        pushToUi(Model.Starting)
        // THEN
        onView(withId(R.id.progress)).check(matches(isDisplayed()))
    }

    @Test
    internal fun when_loading_progress_shows() {
        // WHEN
        pushToUi(Model.Loading)
        // THEN
        onView(withId(R.id.progress)).check(matches(isDisplayed()))
    }

    @Test
    internal fun when_error_progress_does_not_shows() {
        // WHEN
        pushToUi(error)
        // THEN
        onView(withId(R.id.progress)).check(matches(not(isDisplayed())))
    }

    @Test
    internal fun when_error_snack_shows() {
        // WHEN
        pushToUi(error)
        // THEN
        onView(withText(TEXT)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    internal fun when_complete_item_shows() {
        // WHEN
        pushToUi(complete)
        // THEN
        onView(withId(R.id.recyler)).check(matches(hasDescendant(withText(TEXT))))
    }

    @Test
    internal fun when_complete_hides_progress() {
        // WHEN
        pushToUi(complete)
        // THEN
        onView(withId(R.id.progress)).check(matches(not(isDisplayed())))
    }


    private fun pushToUi(model: Model) {
        activityRule.activity.runOnUiThread {
            capturingSlot.captured(model)
        }
    }
}
