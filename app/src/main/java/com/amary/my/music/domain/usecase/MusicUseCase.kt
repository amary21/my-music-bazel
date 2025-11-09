package com.amary.my.music.domain.usecase

import com.amary.my.music.domain.repository.MusicRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MusicUseCase(
    private val musicRepository: MusicRepository,
    private val dispatcher: CoroutineDispatcher,
) {
    suspend fun invoke(query: String) = withContext(dispatcher) {
        try {
            val resultApi = musicRepository.searchMusic(query)
            if (resultApi.isNotEmpty()) {
                musicRepository.insert(resultApi)
                resultApi
            } else {
                throw Exception("No result found")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            musicRepository.getAll()
        }
    }
}