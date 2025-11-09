package com.amary.my.music.domain.usecase

import com.amary.my.music.domain.model.Result
import com.amary.my.music.domain.repository.MusicRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CurrentMusicUseCaseTest {

    private lateinit var musicRepository: MusicRepository
    private lateinit var useCase: CurrentMusicUseCase
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        musicRepository = mockk()
        useCase = CurrentMusicUseCase(musicRepository, testDispatcher)
    }

    @Test
    fun `invoke returns result when music is found by artistId`() = runTest {
        // Given
        val artistId = 123
        val expectedResult = Result(
            artistId = artistId,
            artistName = "Test Artist",
            trackName = "Test Track",
            artworkUrl100 = "https://test.com/image.jpg",
            previewUrl = "https://test.com/preview.mp3"
        )

        coEvery { musicRepository.getByArtistId(artistId) } returns expectedResult

        // When
        val result = useCase.invoke(artistId)

        // Then
        assertNotNull(result)
        assertEquals(artistId, result?.artistId)
        assertEquals("Test Artist", result?.artistName)
        assertEquals("Test Track", result?.trackName)
        assertEquals("https://test.com/image.jpg", result?.artworkUrl100)
        assertEquals("https://test.com/preview.mp3", result?.previewUrl)
        coVerify(exactly = 1) { musicRepository.getByArtistId(artistId) }
    }

    @Test
    fun `invoke returns null when music is not found by artistId`() = runTest {
        // Given
        val artistId = 999

        coEvery { musicRepository.getByArtistId(artistId) } returns null

        // When
        val result = useCase.invoke(artistId)

        // Then
        assertNull(result)
        coVerify(exactly = 1) { musicRepository.getByArtistId(artistId) }
    }

    @Test
    fun `invoke returns correct result for different artistIds`() = runTest {
        // Given
        val artistId1 = 1
        val artistId2 = 2
        val result1 = Result(
            artistId = artistId1,
            artistName = "Artist 1",
            trackName = "Track 1"
        )
        val result2 = Result(
            artistId = artistId2,
            artistName = "Artist 2",
            trackName = "Track 2"
        )

        coEvery { musicRepository.getByArtistId(artistId1) } returns result1
        coEvery { musicRepository.getByArtistId(artistId2) } returns result2

        // When
        val retrievedResult1 = useCase.invoke(artistId1)
        val retrievedResult2 = useCase.invoke(artistId2)

        // Then
        assertEquals("Artist 1", retrievedResult1?.artistName)
        assertEquals("Artist 2", retrievedResult2?.artistName)
        coVerify(exactly = 1) { musicRepository.getByArtistId(artistId1) }
        coVerify(exactly = 1) { musicRepository.getByArtistId(artistId2) }
    }

    @Test
    fun `invoke with zero artistId returns result if exists`() = runTest {
        // Given
        val artistId = 0
        val expectedResult = Result(
            artistId = artistId,
            artistName = "Unknown Artist",
            trackName = "Unknown Track"
        )

        coEvery { musicRepository.getByArtistId(artistId) } returns expectedResult

        // When
        val result = useCase.invoke(artistId)

        // Then
        assertNotNull(result)
        assertEquals(0, result?.artistId)
        assertEquals("Unknown Artist", result?.artistName)
        coVerify(exactly = 1) { musicRepository.getByArtistId(artistId) }
    }

    @Test
    fun `invoke with negative artistId returns null`() = runTest {
        // Given
        val artistId = -1

        coEvery { musicRepository.getByArtistId(artistId) } returns null

        // When
        val result = useCase.invoke(artistId)

        // Then
        assertNull(result)
        coVerify(exactly = 1) { musicRepository.getByArtistId(artistId) }
    }

    @Test
    fun `invoke handles repository exception gracefully`() = runTest {
        // Given
        val artistId = 123

        coEvery { musicRepository.getByArtistId(artistId) } throws Exception("Database error")

        // When & Then
        try {
            useCase.invoke(artistId)
            fail("Expected exception to be thrown")
        } catch (e: Exception) {
            assertEquals("Database error", e.message)
            coVerify(exactly = 1) { musicRepository.getByArtistId(artistId) }
        }
    }

    @Test
    fun `invoke returns result with all fields populated`() = runTest {
        // Given
        val artistId = 456
        val expectedResult = Result(
            artistId = artistId,
            artistName = "Complete Artist",
            artistViewUrl = "https://artist.com",
            trackName = "Complete Track",
            trackViewUrl = "https://track.com",
            artworkUrl100 = "https://artwork100.jpg",
            artworkUrl60 = "https://artwork60.jpg",
            artworkUrl30 = "https://artwork30.jpg",
            previewUrl = "https://preview.mp3",
            collectionName = "Test Album",
            collectionPrice = 9.99,
            trackPrice = 1.29,
            releaseDate = "2025-01-01",
            primaryGenreName = "Rock",
            country = "USA",
            currency = "USD"
        )

        coEvery { musicRepository.getByArtistId(artistId) } returns expectedResult

        // When
        val result = useCase.invoke(artistId)

        // Then
        assertNotNull(result)
        assertEquals("Complete Artist", result?.artistName)
        assertEquals("Complete Track", result?.trackName)
        assertEquals("Test Album", result?.collectionName)
        assertEquals(9.99, result?.collectionPrice ?: 0.0, 0.001)
        assertEquals(1.29, result?.trackPrice ?: 0.0, 0.001)
        assertEquals("Rock", result?.primaryGenreName)
        coVerify(exactly = 1) { musicRepository.getByArtistId(artistId) }
    }
}