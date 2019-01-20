package com.pppp.uscases.usecases

import com.pppp.uscases.Effect
import com.pppp.uscases.Event
import com.spotify.mobius.functions.Consumer
import io.mockk.CapturingSlot
import io.mockk.Runs
import io.mockk.clearMocks
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS

@TestInstance(PER_CLASS)
internal class UseCasesImplTest {
    private val useCase: UseCase = mockk()
    private val consumer: Consumer<Event> = mockk()
    private var slot: CapturingSlot<(Event) -> Unit> = slot()
    private val event: Event = mockk()

    @BeforeEach
    internal fun setUp() {
        clearMocks(consumer, useCase)
        every { useCase.execute(capture(slot)) } answers
                {
                    slot.captured.invoke(event)
                }
        every { consumer.accept(any()) } just Runs
    }

    @Test
    internal fun `when get images then calls network`() {
        // GIVEN
        val uscases = UseCasesImpl(mapOf(Effect.GetAllImages::class.java to useCase))
        // WHEN
        uscases.accept(Effect.GetAllImages, consumer)
        // THEN
        verify(exactly = 1) { useCase.execute(any()) }
        confirmVerified(useCase)
    }

    @Test
    internal fun `when network returns then event is returned`() {
        // GIVEN
        val uscases = UseCasesImpl(mapOf(Effect.GetAllImages::class.java to useCase))
        // WHEN
        uscases.accept(Effect.GetAllImages, consumer)
        // THEN
        verify(exactly = 1) { consumer.accept(event) }
        confirmVerified(consumer)
    }
}