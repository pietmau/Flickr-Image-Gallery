package com.pppp.flickrimagegallery.repository

import com.pppp.entites.Feed
import com.pppp.entites.FlickrImage
import com.pppp.flickrimagegallery.TEXT
import com.pppp.flickrimagegallery.database.FlickrDataBase
import com.pppp.flickrimagegallery.database.getLatestImages
import com.pppp.flickrimagegallery.database.insert
import com.pppp.flickrimagegallery.mapper.Mapper
import com.pppp.flickrimagegallery.pokos.RoomFlickrImage
import com.pppp.network.api.Client
import com.pppp.network.poko.RetrofitFlickrImage
import com.pppp.network.utils.AndroidLogger
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class FlickrRepositoryImplTest {
    private lateinit var repo: FlickrRepository
    @MockK
    private lateinit var client: Client
    @MockK(relaxed = true)
    private lateinit var database: FlickrDataBase
    @MockK
    private lateinit var deferred: Deferred<Feed>
    @MockK(relaxed = true)
    private lateinit var feed: Feed
    @MockK
    private lateinit var mapper: Mapper<FlickrImage, RoomFlickrImage>
    @MockK
    private lateinit var retrofitFlickrImage: RetrofitFlickrImage
    @MockK
    private lateinit var roomFlickrImage: RoomFlickrImage
    @MockK(relaxed = true)
    private lateinit var logger: AndroidLogger

    @BeforeEach
    internal fun setUp() {
        clearMocks(client, mapper, database)
        val list = listOf(retrofitFlickrImage)
        repo = FlickrRepositoryImpl(client, database, mapper, logger)
        every { client.getPics() } returns deferred
        coEvery { deferred.await() } returns feed
        every { feed.images } returns list
        every { mapper.map(retrofitFlickrImage) } returns roomFlickrImage
        runBlocking { repo.getPics() }
    }

    @Test
    internal fun `when getpics asks client first`() = runBlocking {
        verify(exactly = 1) { client.getPics() }
        confirmVerified(client)
    }

    @Test
    internal fun `if get picks then maps them`() = runBlocking {
        verify(exactly = 1) { mapper.map(retrofitFlickrImage) }
        confirmVerified(mapper)
    }

    @Test
    internal fun `when maps then saves`() {
        verify(exactly = 1) { database.insert(any()) }
        confirmVerified(database)
    }

    @Test
    internal fun `when errors queries db`() {
        // GIVEN
        clearMocks(database)
        // WHEN
        every { client.getPics() } throws Exception()
        runBlocking { repo.getPics() }
        // THEN
        verify { database.getLatestImages(MAX_PICS) }
        confirmVerified(database)
    }

    @Test
    internal fun `when errors queries logs`() {
        // GIVEN
        clearMocks(database)
        // WHEN
        every { client.getPics() } throws Exception(TEXT)
        runBlocking { repo.getPics() }
        // THEN
        verify { logger.w(ofType(String::class), ofType(String::class)) }
        confirmVerified(logger)
    }
}
