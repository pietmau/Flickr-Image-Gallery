package com.pppp.uscases.usecases.showDetail

import com.pppp.uscases.Detail
import com.pppp.uscases.Effect
import com.pppp.uscases.Event
import io.mockk.CapturingSlot
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(MockKExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ShowDetailUseCaseTest {
    @MockK(relaxed = true)
    lateinit var effect: Effect.ShowDetail
    @MockK(relaxed = true)
    lateinit var callback: (Event) -> Unit
    private val captor: CapturingSlot<Event> = slot()
    @MockK(relaxed = true)
    lateinit var detail: Detail

    @BeforeEach
    internal fun setUp() {
        every { effect.detail } returns detail
    }

    @Test
    internal fun `when image loaded then show detail`() {
        // GIVEN
        every { effect.imageLoaded } returns true
        //
        ShowDetailUseCase.execute(effect, callback)
        verify { callback.invoke(capture(captor)) }
        confirmVerified(callback)
        assertThat(captor.captured).isInstanceOf(Event.ShowDetail::class.java)
    }
}