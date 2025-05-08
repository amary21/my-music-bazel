package com.amary.my.music.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Pause: ImageVector
    get() {
        if (_Pause != null) {
            return _Pause!!
        }
        _Pause = ImageVector.Builder(
            name = "211871PauseIcon",
            defaultWidth = 512.dp,
            defaultHeight = 512.dp,
            viewportWidth = 512f,
            viewportHeight = 512f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(224f, 435.8f)
                verticalLineTo(76.1f)
                curveToRelative(0f, -6.7f, -5.4f, -12.1f, -12.2f, -12.1f)
                horizontalLineToRelative(-71.6f)
                curveToRelative(-6.8f, 0f, -12.2f, 5.4f, -12.2f, 12.1f)
                verticalLineToRelative(359.7f)
                curveToRelative(0f, 6.7f, 5.4f, 12.2f, 12.2f, 12.2f)
                horizontalLineToRelative(71.6f)
                curveTo(218.6f, 448f, 224f, 442.6f, 224f, 435.8f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(371.8f, 64f)
                horizontalLineToRelative(-71.6f)
                curveToRelative(-6.7f, 0f, -12.2f, 5.4f, -12.2f, 12.1f)
                verticalLineToRelative(359.7f)
                curveToRelative(0f, 6.7f, 5.4f, 12.2f, 12.2f, 12.2f)
                horizontalLineToRelative(71.6f)
                curveToRelative(6.7f, 0f, 12.2f, -5.4f, 12.2f, -12.2f)
                verticalLineTo(76.1f)
                curveTo(384f, 69.4f, 378.6f, 64f, 371.8f, 64f)
                close()
            }
        }.build()

        return _Pause!!
    }

@Suppress("ObjectPropertyName")
private var _Pause: ImageVector? = null
