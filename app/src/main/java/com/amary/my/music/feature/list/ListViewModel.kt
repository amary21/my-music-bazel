package com.amary.my.music.feature.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amary.my.music.data.exoplayer.MusicPlayer
import com.amary.my.music.data.exoplayer.PlayerListener
import com.amary.my.music.domain.model.Result
import com.amary.my.music.domain.usecase.ListMusicUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListViewModel(
    private val musicUseCase: ListMusicUseCase,
    private val musicPlayer: MusicPlayer
) : ViewModel() {

    private var positionJob: Job? = null
    private val _state = MutableStateFlow(ListState())
    val state get() = _state.asStateFlow()

    init {
        setupPlayerListener()
    }

    private fun setupPlayerListener() {
        musicPlayer.setListener(object : PlayerListener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                _state.update {
                    it.copy(isPlaying = isPlaying)
                }
            }

            override fun onPlaybackStateChanged(isReady: Boolean) {
                if (isReady) {
                    _state.update {
                        it.copy(duration = musicPlayer.duration)
                    }
                }
            }
        })
    }

    fun onEvent(event: ListEvent) {
        when (event) {
            is ListEvent.OnBack -> Unit
            is ListEvent.OnSearch -> searchMusic(event.query)
            is ListEvent.OnPrepare -> prepare(event.result)
            is ListEvent.OnSeekTo -> seekTo(event.position)
            is ListEvent.OnPlayPause -> playPause()
            is ListEvent.OnNext -> next()
            is ListEvent.OnPrevious -> prev()
        }
    }

    private fun searchMusic(query: String) = viewModelScope.launch {
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

    private fun prepare(result: Result) {
        musicPlayer.prepare(result.previewUrl)

        _state.update {
            it.copy(selectedSong = result)
        }

        startPositionUpdate()
    }

    private fun startPositionUpdate() {
        positionJob?.cancel()
        positionJob = viewModelScope.launch {
            while (true) {
                _state.update {
                    it.copy(position = musicPlayer.currentPosition)
                }
                delay(500)
            }
        }
    }

    private fun playPause() {
        if (musicPlayer.isPlaying) {
            musicPlayer.pause()
        } else {
            musicPlayer.play()
        }
    }

    private fun seekTo(position: Long) {
        musicPlayer.seekTo(position)
    }

    private fun next() {
        val current = _state.value.selectedSong
        val results = _state.value.results
        val currentIndex = results.indexOfFirst { it.trackId == current?.trackId }

        if (currentIndex != -1 && currentIndex < results.lastIndex) {
            val nextSong = results[currentIndex + 1]
            prepare(nextSong)
        }
    }

    private fun prev() {
        val current = _state.value.selectedSong
        val results = _state.value.results
        val currentIndex = results.indexOfFirst { it.trackId == current?.trackId }

        if (currentIndex > 0) {
            val prevSong = results[currentIndex - 1]
            prepare(prevSong)
        }
    }

    override fun onCleared() {
        musicPlayer.release()
        positionJob?.cancel()
        super.onCleared()
    }
}