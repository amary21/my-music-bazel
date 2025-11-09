package com.amary.my.music.domain.usecase

import com.amary.my.music.domain.repository.MusicRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class CurrentMusicUseCase(
    private val musicRepository: MusicRepository,
    private val dispatcher: CoroutineDispatcher,
) {
    suspend fun invoke(artistId: Int) = withContext(dispatcher) {
        musicRepository.getByArtistId(artistId)
    }
}