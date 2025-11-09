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
class ListMusicUseCaseTest {

    private lateinit var musicRepository: MusicRepository
    private lateinit var useCase: ListMusicUseCase
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        musicRepository = mockk()
        useCase = ListMusicUseCase(musicRepository, testDispatcher)
    }

    @Test
    fun `invoke with query returns results from API when successful`() = runTest {
        // Given
        val query = "test query"
        val apiResults = listOf(
            Result(
                artistId = 1,
                artistName = "Artist 1",
                trackName = "Track 1"
            ),
            Result(
                artistId = 2,
                artistName = "Artist 2",
                trackName = "Track 2"
            )
        )

        coEvery { musicRepository.searchMusic(query) } returns apiResults
        coEvery { musicRepository.insert(apiResults) } returns Unit

        // When
        val result = useCase.invoke(query)

        // Then
        assertEquals(2, result.size)
        assertEquals("Artist 1", result[0].artistName)
        assertEquals("Artist 2", result[1].artistName)
        coVerify(exactly = 1) { musicRepository.searchMusic(query) }
        coVerify(exactly = 1) { musicRepository.insert(apiResults) }
    }

    @Test
    fun `invoke with query returns database results when API returns empty list`() = runTest {
        // Given
        val query = "test query"
        val databaseResults = listOf(
            Result(
                artistId = 3,
                artistName = "Cached Artist",
                trackName = "Cached Track"
            )
        )

        coEvery { musicRepository.searchMusic(query) } returns emptyList()
        coEvery { musicRepository.getAll() } returns databaseResults

        // When
        val result = useCase.invoke(query)

        // Then
        assertEquals(1, result.size)
        assertEquals("Cached Artist", result[0].artistName)
        coVerify(exactly = 1) { musicRepository.searchMusic(query) }
        coVerify(exactly = 1) { musicRepository.getAll() }
        coVerify(exactly = 0) { musicRepository.insert(any()) }
    }

    @Test
    fun `invoke with query returns database results when API throws exception`() = runTest {
        // Given
        val query = "test query"
        val databaseResults = listOf(
            Result(
                artistId = 4,
                artistName = "Fallback Artist",
                trackName = "Fallback Track"
            )
        )

        coEvery { musicRepository.searchMusic(query) } throws Exception("Network error")
        coEvery { musicRepository.getAll() } returns databaseResults

        // When
        val result = useCase.invoke(query)

        // Then
        assertEquals(1, result.size)
        assertEquals("Fallback Artist", result[0].artistName)
        coVerify(exactly = 1) { musicRepository.searchMusic(query) }
        coVerify(exactly = 1) { musicRepository.getAll() }
        coVerify(exactly = 0) { musicRepository.insert(any()) }
    }

    @Test
    fun `invoke without query returns all results from database`() = runTest {
        // Given
        val databaseResults = listOf(
            Result(
                artistId = 5,
                artistName = "DB Artist 1",
                trackName = "DB Track 1"
            ),
            Result(
                artistId = 6,
                artistName = "DB Artist 2",
                trackName = "DB Track 2"
            ),
            Result(
                artistId = 7,
                artistName = "DB Artist 3",
                trackName = "DB Track 3"
            )
        )

        coEvery { musicRepository.getAll() } returns databaseResults

        // When
        val result = useCase.invoke()

        // Then
        assertEquals(3, result.size)
        assertEquals("DB Artist 1", result[0].artistName)
        assertEquals("DB Artist 2", result[1].artistName)
        assertEquals("DB Artist 3", result[2].artistName)
        coVerify(exactly = 1) { musicRepository.getAll() }
        coVerify(exactly = 0) { musicRepository.searchMusic(any()) }
    }

    @Test
    fun `invoke without query returns empty list when database is empty`() = runTest {
        // Given
        coEvery { musicRepository.getAll() } returns emptyList()

        // When
        val result = useCase.invoke()

        // Then
        assertTrue(result.isEmpty())
        coVerify(exactly = 1) { musicRepository.getAll() }
    }

    @Test
    fun `invoke with query inserts API results before returning them`() = runTest {
        // Given
        val query = "test query"
        val apiResults = listOf(
            Result(artistId = 1, artistName = "Artist", trackName = "Track")
        )

        coEvery { musicRepository.searchMusic(query) } returns apiResults
        coEvery { musicRepository.insert(apiResults) } returns Unit

        // When
        useCase.invoke(query)

        // Then
        coVerify(ordering = io.mockk.Ordering.ORDERED) {
            musicRepository.searchMusic(query)
            musicRepository.insert(apiResults)
        }
    }

    @Test
    fun `invoke with query handles exception during insert and returns database results`() = runTest {
        // Given
        val query = "test query"
        val apiResults = listOf(
            Result(artistId = 1, artistName = "Artist", trackName = "Track")
        )
        val databaseResults = listOf(
            Result(artistId = 2, artistName = "DB Artist", trackName = "DB Track")
        )

        coEvery { musicRepository.searchMusic(query) } returns apiResults
        coEvery { musicRepository.insert(apiResults) } throws Exception("Database error")
        coEvery { musicRepository.getAll() } returns databaseResults

        // When
        val result = useCase.invoke(query)

        // Then
        assertEquals(1, result.size)
        assertEquals("DB Artist", result[0].artistName)
        coVerify(exactly = 1) { musicRepository.searchMusic(query) }
        coVerify(exactly = 1) { musicRepository.getAll() }
    }
}