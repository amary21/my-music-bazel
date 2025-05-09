package com.amary.my.music.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Next: ImageVector
    get() {
        if (_Next != null) {
            return _Next!!
        }
        _Next = ImageVector.Builder(
            name = "Next",
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
                moveTo(5.988f, 4f)
                lineTo(16.233f, 12f)
                lineTo(5.988f, 20f)
                verticalLineTo(4f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFF2F2F2)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(20.331f, 5f)
                verticalLineTo(19f)
            }
        }.build()

        return _Next!!
    }

@Suppress("ObjectPropertyName")
private var _Next: ImageVector? = null
