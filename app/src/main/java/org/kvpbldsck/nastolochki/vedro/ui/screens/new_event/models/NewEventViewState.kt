package org.kvpbldsck.nastolochki.vedro.ui.screens.new_event.models

import org.kvpbldsck.nastolochki.vedro.models.User
import org.kvpbldsck.nastolochki.vedro.utils.ceilingTimeTo
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime

data class NewEventViewState(
    val type: NewEventTypeEnum,
    val title: String?,
    val description: String?,
    val address: String?,
    val participants: List<User>,
    val singleDate: NewEventDateTime,
    val possibleDates: List<LocalDateTime>
){
    companion object {
        fun getTestState(type: NewEventTypeEnum = NewEventTypeEnum.ExactTime) = NewEventViewState(
            type,
            null,
            null,
            null,
            User.getTestUsers(),
            NewEventDateTime(LocalDate.now(), null),
            listOf(
                LocalDateTime.now().ceilingTimeTo(Duration.ofMinutes(30)),
                LocalDateTime.now().plusDays(1).ceilingTimeTo(Duration.ofMinutes(30)),
                LocalDateTime.now().plusDays(2).ceilingTimeTo(Duration.ofMinutes(30)),
            )
        )
    }
}