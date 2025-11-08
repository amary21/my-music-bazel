package com.amary.my.music.feature

import com.amary.my.music.domain.model.Result

data class MusicState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val results: List<Result> = emptyList(),
    val message: String = "",
    val isPlaying: Boolean = false,
    val position: Long = 0,
    val duration: Long = 0,
    val selectedSong: Result? = null
)
