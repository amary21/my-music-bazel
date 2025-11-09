package com.amary.my.music.feature.list

import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.amary.my.music.feature.detail.DetailBottomSheet
import com.amary.my.music.feature.detail.DetailRoute
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
@SerialName("list")
data object ListRoute

fun NavGraphBuilder.listScreen(navController: NavHostController) {
    composable<ListRoute> {
        val viewModel: ListViewModel = koinViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()

        val scope = rememberCoroutineScope()
        val sheetState = rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden,
            skipHalfExpanded = true
        )

        ListScreen(
            state = state,
            event = { uiEvent ->
                when(uiEvent) {
                    is ListEvent.OnPrepare -> {
//                        scope.launch { sheetState.show() }
//                        viewModel.onEvent(uiEvent)
                        navController.navigate(
                            DetailRoute(
                                artistId = uiEvent.result.artistId
                            )
                        )
                    }
                    else -> viewModel.onEvent(uiEvent)
                }

            },
        )

        DetailBottomSheet(
            state = state,
            sheetState = sheetState,
            event = { uiEvent ->
                when(uiEvent) {
                    is ListEvent.OnBack -> {
                        scope.launch { sheetState.hide() }
                    }
                    else -> viewModel.onEvent(uiEvent)
                }

            },
        )
    }
}