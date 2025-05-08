package com.amary.my.music.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Play: ImageVector
    get() {
        if (_Play != null) {
            return _Play!!
        }
        _Play = ImageVector.Builder(
            name = "211876PlayIcon",
            defaultWidth = 512.dp,
            defaultHeight = 512.dp,
            viewportWidth = 512f,
            viewportHeight = 512f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(405.2f, 232.9f)
                lineTo(126.8f, 67.2f)
                curveToRelative(-3.4f, -2f, -6.9f, -3.2f, -10.9f, -3.2f)
                curveToRelative(-10.9f, 0f, -19.8f, 9f, -19.8f, 20f)
                horizontalLineTo(96f)
                verticalLineToRelative(344f)
                horizontalLineToRelative(0.1f)
                curveToRelative(0f, 11f, 8.9f, 20f, 19.8f, 20f)
                curveToRelative(4.1f, 0f, 7.5f, -1.4f, 11.2f, -3.4f)
                lineToRelative(278.1f, -165.5f)
                curveToRelative(6.6f, -5.5f, 10.8f, -13.8f, 10.8f, -23.1f)
                curveTo(416f, 246.7f, 411.8f, 238.5f, 405.2f, 232.9f)
                close()
            }
        }.build()

        return _Play!!
    }

@Suppress("ObjectPropertyName")
private var _Play: ImageVector? = null
