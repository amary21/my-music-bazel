package com.amary.my.music.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColors = darkColors(
    primary = Purple80,
    secondary = PurpleGrey80,
    onPrimary = Pink80,
)

private val LightColors = lightColors(
    primary = Purple40,
    secondary = PurpleGrey40,
    onPrimary = Pink40,
)

@Composable
fun MusicTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colors = colors,
        typography = Typography,
        content = content
    )
}