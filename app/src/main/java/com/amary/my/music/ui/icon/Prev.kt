package com.amary.my.music.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Prev: ImageVector
    get() {
        if (_Prev != null) {
            return _Prev!!
        }
        _Prev = ImageVector.Builder(
            name = "Prev",
            defaultWidth = 26.dp,
            defaultHeight = 24.dp,
            viewportWidth = 26f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFF2F2F2)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(20.012f, 20f)
                lineTo(9.767f, 12f)
                lineTo(20.012f, 4f)
                verticalLineTo(20f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFF2F2F2)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(5.669f, 19f)
                verticalLineTo(5f)
            }
        }.build()

        return _Prev!!
    }

@Suppress("ObjectPropertyName")
private var _Prev: ImageVector? = null
