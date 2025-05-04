package com.amary.my.music.domain.api

import com.amary.my.music.data.api.model.Result

interface MusicUseCase {
    suspend fun invoke(query: String): List<Result>
}