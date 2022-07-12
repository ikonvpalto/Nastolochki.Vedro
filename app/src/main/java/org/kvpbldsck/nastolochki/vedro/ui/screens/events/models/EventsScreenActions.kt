package org.kvpbldsck.nastolochki.vedro.ui.screens.events.models

import java.time.LocalDate

sealed class EventsScreenActions {
    data class ScrollDateToView(val date: LocalDate): EventsScreenActions()
}