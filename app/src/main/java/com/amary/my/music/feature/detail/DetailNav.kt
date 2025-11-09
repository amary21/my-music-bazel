package com.amary.my.music.feature.detail

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
@SerialName("detail")
data class DetailRoute(
    val artistId: Int?
)

fun NavGraphBuilder.detailScreen(navController: NavHostController) {
    composable<DetailRoute> { backStackEntry ->
        val route = backStackEntry.toRoute<DetailRoute>()
        val viewModel: DetailViewModel = koinViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()

        LaunchedEffect(route) {
            if(route.artistId == null) return@LaunchedEffect

            viewModel.onEvent(
                DetailEvent.OnArgument(
                    artistId = route.artistId,
                )
            )
        }

        DetailScreen(
            state = state,
            event = { uiEvent ->
                when(uiEvent) {
                    is DetailEvent.OnBack -> navController.popBackStack()
                    else -> viewModel.onEvent(uiEvent)
                }
            },
        )
    }
}