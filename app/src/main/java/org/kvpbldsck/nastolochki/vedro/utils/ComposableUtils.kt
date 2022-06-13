package org.kvpbldsck.nastolochki.vedro.utils

import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyListLayoutInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun Dp.toPixels(): Float {
    val value = this
    return LocalDensity.current.run { value.toPx() }
}

fun animateListCornerElements(item: LazyListItemInfo, layout: LazyListLayoutInfo, offset: Float = 8f, startValue: Float = 0f, endValue: Float = 1f): Float {
    // at the start of list
    if (item.offset <= offset) {
        val progress = (item.offset + item.size) / (offset + item.size)
        return progress * (endValue - startValue) + startValue
    }

    // at the end of list
    if (layout.viewportEndOffset - item.offset - item.size <= offset) {
        val progress = (layout.viewportEndOffset - item.offset.toFloat()) / (offset + item.size)
        return progress * (endValue - startValue) + startValue
    }

    return endValue
}