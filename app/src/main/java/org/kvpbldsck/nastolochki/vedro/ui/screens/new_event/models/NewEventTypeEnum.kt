package org.kvpbldsck.nastolochki.vedro.ui.screens.new_event.models

import androidx.annotation.StringRes
import org.kvpbldsck.nastolochki.vedro.R

enum class NewEventTypeEnum(val index: Int, @StringRes val titleRes: Int) {
    ExactTime(0, R.string.on_exact_time),
    WithVoting(1, R.string.with_voting);

    companion object {
        fun sortedValues() = NewEventTypeEnum.values().sortedBy { it.index }
    }
}