package com.amary.my.music.feature.detail

import android.net.Uri
import android.os.Bundle
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.amary.my.music.domain.model.Result
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.androidx.compose.koinViewModel
import kotlin.reflect.typeOf

object ResultNavType : NavType<Result>(isNullableAllowed = true) {
    override fun put(bundle: Bundle, key: String, value: Result) {
        bundle.putString(key, Json.encodeToString(value))
    }

    override fun get(bundle: Bundle, key: String): Result? {
        return bundle.getString(key)?.let { Json.decodeFromString(it) }
    }

    override fun parseValue(value: String): Result {
        return Json.decodeFromString(Uri.decode(value))
    }

    override fun serializeAsValue(value: Result): String {
        return Uri.encode(Json.encodeToString(value))
    }
}

object ResultListNavType : NavType<List<Result>?>(isNullableAllowed = true) {
    override fun put(bundle: Bundle, key: String, value: List<Result>?) {
        bundle.putString(key, value?.let { Json.encodeToString(it) })
    }

    override fun get(bundle: Bundle, key: String): List<Result>? {
        return bundle.getString(key)?.let { Json.decodeFromString(it) }
    }

    override fun parseValue(value: String): List<Result>? {
        return if (value == "null") null else Json.decodeFromString(Uri.decode(value))
    }

    override fun serializeAsValue(value: List<Result>?): String {
        return value?.let { Uri.encode(Json.encodeToString(it)) } ?: "null"
    }
}

@Serializable
@SerialName("detail")
data class DetailRoute(
    val results: List<Result>? = emptyList(),
    val currentResult: Result? = null
)

fun NavGraphBuilder.detailScreen(navController: NavHostController) {
    composable<DetailRoute>(
        typeMap = mapOf(
            typeOf<Result?>() to ResultNavType,
            typeOf<List<Result>?>() to ResultListNavType
        )
    ) { backStackEntry ->
        val route = backStackEntry.toRoute<DetailRoute>()
        val viewModel: DetailViewModel = koinViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()

        LaunchedEffect(route) {
            if(route.currentResult == null || route.results == null) return@LaunchedEffect

            viewModel.onEvent(
                DetailEvent.OnArgument(
                    results = route.results,
                    currentResult = route.currentResult
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