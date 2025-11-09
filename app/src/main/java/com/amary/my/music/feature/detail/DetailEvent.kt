package com.amary.my.music.feature.detail

import com.amary.my.music.domain.model.Result

sealed class DetailEvent {
    data object OnBack : DetailEvent()
    data class OnArgument(val artistId: Int): DetailEvent()
    data class OnSeekTo(val position: Long): DetailEvent()
    data object OnPlayPause : DetailEvent()
    data object OnNext : DetailEvent()
    data object OnPrevious : DetailEvent()
}