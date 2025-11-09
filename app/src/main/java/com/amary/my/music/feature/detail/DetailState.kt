package com.amary.my.music.feature.detail

import com.amary.my.music.domain.model.Result

data class DetailState(
    val artistId: Int = 0,
    val results: List<Result> = emptyList(),
    val isPlaying: Boolean = false,
    val position: Long = 0,
    val duration: Long = 0,
    val selectedSong: Result? = null
)
