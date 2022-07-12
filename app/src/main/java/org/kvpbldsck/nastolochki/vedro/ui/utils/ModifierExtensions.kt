package org.kvpbldsck.nastolochki.vedro.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke

fun Modifier.drawUnderline(strokeWidth: Float, color: Color): Modifier {
    return drawBehind {
        val size = this.size
        drawPath(
            path = Path().apply {
                moveTo(0f, size.height)
                lineTo(size.width, size.height)
            },
            color = color,
            style = Stroke(width = strokeWidth)
        )
    }
}