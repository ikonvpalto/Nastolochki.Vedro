package org.kvpbldsck.nastolochki.vedro.ui.screens.events.models

import org.kvpbldsck.nastolochki.vedro.ui.models.UserModel
import java.time.LocalDateTime
import java.util.*

data class EventModel(
    val id: UUID,
    val title: String,
    val address: String,
    val participants: List<UserModel>,
    val possibleDates: List<LocalDateTime>,
    val votedDates: List<LocalDateTime>,
    val isVoted: Boolean,
    val isDateSelected: Boolean,
    val selectedDate: LocalDateTime?
)