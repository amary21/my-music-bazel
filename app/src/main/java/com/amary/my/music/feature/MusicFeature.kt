package com.amary.my.music.feature

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.androidx.compose.koinViewModel

@Composable
fun MusicFeature() {
    val viewModel: MusicViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.searchMusic("APT")
    }

    MusicScreen(
        state = state,
        onSearch = {
            viewModel.searchMusic(it)
        },
        onPrepare = {
            viewModel.prePare(it.previewUrl)
        },
        onSeekTo = {
            viewModel.seekTo(it)
        },
        onPlay = {
            viewModel.playPause()
        }
    )
}