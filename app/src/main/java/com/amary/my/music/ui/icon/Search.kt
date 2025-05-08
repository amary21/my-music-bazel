package com.amary.my.music.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Search: ImageVector
    get() {
        if (_Search != null) {
            return _Search!!
        }
        _Search = ImageVector.Builder(
            name = "Search",
            defaultWidth = 18.dp,
            defaultHeight = 18.dp,
            viewportWidth = 18f,
            viewportHeight = 18f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFF8E8E8E)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(8.25f, 14.25f)
                curveTo(11.564f, 14.25f, 14.25f, 11.564f, 14.25f, 8.25f)
                curveTo(14.25f, 4.936f, 11.564f, 2.25f, 8.25f, 2.25f)
                curveTo(4.936f, 2.25f, 2.25f, 4.936f, 2.25f, 8.25f)
                curveTo(2.25f, 11.564f, 4.936f, 14.25f, 8.25f, 14.25f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFF8E8E8E)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(15.75f, 15.75f)
                lineTo(12.488f, 12.488f)
            }
        }.build()

        return _Search!!
    }

@Suppress("ObjectPropertyName")
private var _Search: ImageVector? = null
