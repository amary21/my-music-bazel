package com.amary.my.music.domain.implementation

import com.amary.my.music.data.api.model.Result
import com.amary.my.music.data.api.repository.MusicRepository
import com.amary.my.music.domain.api.MusicUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MusicUseCaseImpl(
    private val musicRepository: MusicRepository,
    private val dispatcher: CoroutineDispatcher
): MusicUseCase {
    override suspend fun invoke(query: String): List<Result> {
        return withContext(dispatcher) {
            musicRepository.searchMusic(query)
        }
    }
}