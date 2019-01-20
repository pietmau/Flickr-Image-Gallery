package com.pppp.flickrimagegallery

import com.pppp.entites.Feed
import com.pppp.entites.FlickrImage
import com.pppp.flickrimagegallery.repository.FlickrRepository
import com.pppp.network.utils.AndroidLogger
import com.pppp.uscases.Event
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.Unconfined
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.api.extension.ExtendWith

const val TEXT = "text"

@ExtendWith(MockKExtension::class)
@TestInstance(PER_CLASS)
internal class FlickrRepositoryUseCaseTest {
    private lateinit var useCase: RepositoryUseCase
    @MockK(relaxed = true)
    private lateinit var handler: (Event) -> Unit
    @MockK
    private lateinit var deferred: Deferred<Feed>
    @MockK
    private lateinit var feed: Feed
    private var images: List<FlickrImage> = emptyList()
    private val eventcaptor: CapturingSlot<Event.LoadComplete> = slot()
    @MockK
    private lateinit var repo: FlickrRepository
    @MockK(relaxed = true)
    private lateinit var logger: AndroidLogger

    @BeforeEach
    internal fun setUp() {
        clearMocks(handler)
        useCase = RepositoryUseCase(repo, logger, Unconfined)
        coEvery() { repo.getPics() } returns images
        coEvery { deferred.await() } returns feed
        every { feed.entry } returns images
        useCase.execute(handler)
    }

    @Test
    internal fun `when starts gets feeds`() {
        coVerify(exactly = 1) { repo.getPics() }
        confirmVerified(repo)
    }


    @Test
    internal fun `when gets feeds sends them`() {
        verify(exactly = 1) { handler(capture(eventcaptor)) }
        confirmVerified(handler)
    }

    @Test
    internal fun `sends then sends the result`() {
        verify(exactly = 1) { handler(capture(eventcaptor)) }
        assertEquals(images, eventcaptor.captured.images)
    }

    @Test
    internal fun `error then sends error`() {
        // GIVEN
        coEvery { repo.getPics() } throws Exception()
        val handler: (Event) -> Unit = mockk()
        every { handler.invoke(any()) } just Runs
        // WHEN
        useCase.execute(handler)
        // THEN
        verify(exactly = 1) { handler(ofType(Event.Error::class)) }
        confirmVerified(handler)
    }

    @Test
    internal fun `error then logs`() {
        // GIVEN
        coEvery { repo.getPics() } throws Exception(TEXT)
        val handler: (Event) -> Unit = mockk()
        every { handler.invoke(any()) } just Runs
        // WHEN
        useCase.execute(handler)
        // THEN
        verify(exactly = 1) { logger.w(ofType<String>(), ofType<String>()) }
        confirmVerified(logger)
    }
}