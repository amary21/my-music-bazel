package com.amary.my.music.feature

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MusicFeature() {
    val viewModel: MusicViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    MusicScreen(
        state = state,
        onSearch = {
            viewModel.searchMusic(it)
        },
        onPrepare = {
            viewModel.prePare(it)
            scope.launch { sheetState.show() }
        },
        onSeekTo = {
            viewModel.seekTo(it)
        },
        onPlay = {
            viewModel.playPause()
        }
    )

    MusicBottomSheet(
        state = state,
        sheetState = sheetState,
        onBack = {
            scope.launch { sheetState.hide() }
        },
        onSeekTo = {
            viewModel.seekTo(it)
        },
        onPlay = {
            viewModel.playPause()
        },
        onPrevious = {
            viewModel.prev()
        },
        onNext = {
            viewModel.next()
        }
    )
}