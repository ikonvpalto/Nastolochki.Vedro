package org.kvpbldsck.nastolochki.vedro.ui.screens.events.models

import java.time.LocalDate
import java.time.LocalDateTime

sealed class EventsScreenEvents {
    data class DateSelected(val date: LocalDate, val isScrollingNeeded: Boolean) : EventsScreenEvents()
    data class CalendarMonthChanged(val month: String) : EventsScreenEvents()
    data class EventDateToggled(val event: EventModel, val date: LocalDateTime, val checked: Boolean) : EventsScreenEvents()
    data class EventVoteToggled(val event: EventModel, val isVoted: Boolean) : EventsScreenEvents()
}

