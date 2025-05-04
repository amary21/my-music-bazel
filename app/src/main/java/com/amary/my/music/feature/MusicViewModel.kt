package com.amary.my.music.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.amary.my.music.domain.api.MusicUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MusicViewModel(
    private val musicUseCase: MusicUseCase,
    private val exoPlayer: ExoPlayer
): ViewModel() {
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

    fun prePare(url: String) {
        exoPlayer.setMediaItem(MediaItem.fromUri(url))
        exoPlayer.prepare()

        exoPlayer.addListener(object : Player.Listener{
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                super.onIsPlayingChanged(isPlaying)
                _state.update {
                    it.copy(
                        isPlaying = isPlaying
                    )
                }
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                super.onPlaybackStateChanged(playbackState)
                _state.update {
                    it.copy(
                        duration = exoPlayer.duration,
                        position = exoPlayer.currentPosition
                    )
                }
            }
        })
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

    override fun onCleared() {
        exoPlayer.release()
        super.onCleared()
    }
}