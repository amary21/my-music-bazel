package com.amary.my.music.feature

import com.amary.my.music.domain.model.Result

sealed class MusicEvent {
    data object OnBack : MusicEvent()
    data class OnSearch(val query: String) : MusicEvent()
    data class OnPrepare(val result: Result): MusicEvent()
    data class OnSeekTo(val position: Long): MusicEvent()
    data object OnPlayPause : MusicEvent()
    data object OnNext : MusicEvent()
    data object OnPrevious : MusicEvent()
}