package com.amary.my.music.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.amary.my.music.domain.model.Result
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val exoPlayer: ExoPlayer
): ViewModel() {
    private var positionJob: Job? = null
    private val _state = MutableStateFlow(DetailState())
    val state get() = _state.asStateFlow()

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.OnBack -> Unit
            is DetailEvent.OnArgument -> setArgument(event.results, event.currentResult)
            is DetailEvent.OnSeekTo -> seekTo(event.position)
            is DetailEvent.OnPlayPause -> playPause()
            is DetailEvent.OnNext -> next()
            is DetailEvent.OnPrevious -> prev()
        }
    }

    private fun setArgument(results1: List<Result>, currentResult: Result) {
        _state.update {
            it.copy(
                results = results1,
                selectedSong = currentResult
            )
        }
        prePare(currentResult)
    }

    private fun prePare(result: Result) {
        exoPlayer.setMediaItem(MediaItem.fromUri(result.previewUrl))
        exoPlayer.prepare()

        exoPlayer.addListener(object : Player.Listener{
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                _state.update {
                    it.copy(
                        isPlaying = isPlaying,
                        selectedSong = result
                    )
                }
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == Player.STATE_READY) {
                    _state.update {
                        it.copy(
                            duration = exoPlayer.duration
                        )
                    }
                }
            }
        })

        startPositionUpdate()
    }

    private fun startPositionUpdate() {
        positionJob = viewModelScope.launch {
            while (true) {
                _state.update {
                    it.copy(
                        position = exoPlayer.currentPosition
                    )
                }
                delay(500)
            }
        }
    }

    private fun playPause() {
        if (exoPlayer.isPlaying) {
            exoPlayer.pause()
        } else {
            exoPlayer.play()
        }
    }

    fun seekTo(position: Long) {
        exoPlayer.seekTo(position)
    }

    private fun next() {
        val current = _state.value.selectedSong
        val results = _state.value.results
        val currentIndex = results.indexOfFirst { it.trackId == current?.trackId }

        if (currentIndex != -1 && currentIndex < results.lastIndex) {
            val nextSong = results[currentIndex + 1]
            prePare(nextSong)
        }
    }

    private fun prev() {
        val current = _state.value.selectedSong
        val results = _state.value.results
        val currentIndex = results.indexOfFirst { it.trackId == current?.trackId }

        if (currentIndex > 0) {
            val prevSong = results[currentIndex - 1]
            prePare(prevSong)
        }
    }


    override fun onCleared() {
        exoPlayer.release()
        positionJob?.cancel()
        super.onCleared()
    }
}