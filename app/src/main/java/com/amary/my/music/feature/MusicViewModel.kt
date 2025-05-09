package com.amary.my.music.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.amary.my.music.data.api.model.Result
import com.amary.my.music.domain.api.MusicUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MusicViewModel(
    private val musicUseCase: MusicUseCase,
    private val exoPlayer: ExoPlayer
): ViewModel() {
    private var positionJob: Job? = null
    private val _state = MutableStateFlow(MusicState())
    val state get() = _state.asStateFlow()

    fun searchMusic(query: String)= viewModelScope.launch {
        try {
            _state.update {
                it.copy(
                    isLoading = true,
                    isError = false,
                    results = emptyList(),
                    message = ""
                )
            }


            val useCase = musicUseCase.invoke(query)
            _state.update {
                it.copy(
                    isLoading = false,
                    isError = false,
                    results = useCase,
                    message = ""
                )
            }
        } catch (e: Exception) {
            _state.update {
                it.copy(
                    isLoading = false,
                    isError = true,
                    results = emptyList(),
                    message = e.message.orEmpty()
                )
            }
        }
    }

    fun prePare(result: Result) {
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

    fun playPause() {
        if (exoPlayer.isPlaying) {
            exoPlayer.pause()
        } else {
            exoPlayer.play()
        }
    }

    fun seekTo(position: Long) {
        exoPlayer.seekTo(position)
    }

    fun next() {
        val current = _state.value.selectedSong
        val results = _state.value.results
        val currentIndex = results.indexOfFirst { it.trackId == current?.trackId }

        if (currentIndex != -1 && currentIndex < results.lastIndex) {
            val nextSong = results[currentIndex + 1]
            prePare(nextSong)
        }
    }

    fun prev() {
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