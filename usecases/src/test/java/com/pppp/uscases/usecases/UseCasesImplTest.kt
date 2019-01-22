package com.pppp.uscases.usecases

import com.pppp.uscases.UseCase
import com.pppp.uscases.main.events.Effect
import com.pppp.uscases.main.events.Event
import io.mockk.CapturingSlot
import io.mockk.Runs
import io.mockk.clearMocks
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
@TestInstance(PER_CLASS)
internal class UseCasesImplTest {
    @MockK(relaxed = true)
    private lateinit var useCase: UseCase<Effect, Event>
    @MockK
    private lateinit var consumer: (Event) -> Unit
    private var slot: CapturingSlot<(Event) -> Unit> = slot()
    @MockK
    private lateinit var event: Event

    @BeforeEach
    internal fun setUp() {
        clearMocks(consumer, useCase)
        every { useCase.execute(Effect.GetAllImages, capture(slot)) } answers
                {
                    slot.captured.invoke(event)
                }
        every { consumer(any()) } just Runs
    }

    @Test
    internal fun `when get images then calls network`() {
        // GIVEN
        val uscases = mapOf(Effect.GetAllImages::class.java to useCase)
        val usecase = uscases[Effect.GetAllImages::class.java]
        // WHEN
        usecase?.execute(Effect.GetAllImages, consumer)
        // THEN
        verify(exactly = 1) {
            useCase.execute(
                ofType(Effect.GetAllImages::class), ofType<(Event) -> Unit>()
            )
        }
        confirmVerified(useCase)
    }

    @Test
    internal fun `when network returns then event is returned`() {
        // GIVEN
        val uscases = mapOf(Effect.GetAllImages::class.java to useCase)
        val usecase = uscases[Effect.GetAllImages::class.java]
        // WHEN
        usecase?.execute(Effect.GetAllImages, consumer)
        // THEN
        verify(exactly = 1) { consumer(event) }
        confirmVerified(consumer)
    }
}