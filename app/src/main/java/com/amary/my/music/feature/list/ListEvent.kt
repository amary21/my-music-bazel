package com.amary.my.music.feature.list

import com.amary.my.music.domain.model.Result

sealed class ListEvent {
    data object OnBack : ListEvent()
    data class OnSearch(val query: String) : ListEvent()
    data class OnPrepare(val result: Result): ListEvent()
    data class OnSeekTo(val position: Long): ListEvent()
    data object OnPlayPause : ListEvent()
    data object OnNext : ListEvent()
    data object OnPrevious : ListEvent()
}