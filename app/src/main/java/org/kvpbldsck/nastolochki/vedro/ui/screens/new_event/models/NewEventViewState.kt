package org.kvpbldsck.nastolochki.vedro.ui.screens.new_event.models

import org.kvpbldsck.nastolochki.vedro.ui.models.UserModel
import org.kvpbldsck.nastolochki.vedro.utils.ceilingTimeTo
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime

data class NewEventViewState(
    val type: NewEventTypeEnum = NewEventTypeEnum.ExactTime,
    val title: String? = null,
    val description: String? = null,
    val address: String? = null,
    val participants: List<UserModel> = emptyList(),
    val singleDate: NewEventDateTime = NewEventDateTime(),
    val possibleDates: List<LocalDateTime> = emptyList()
){
    companion object {
        fun getTestState(type: NewEventTypeEnum = NewEventTypeEnum.ExactTime) = NewEventViewState(
            type,
            null,
            null,
            null,
            UserModel.getTestUsers(),
            NewEventDateTime(LocalDate.now(), null),
            listOf(
                LocalDateTime.now().ceilingTimeTo(Duration.ofMinutes(30)),
                LocalDateTime.now().plusDays(1).ceilingTimeTo(Duration.ofMinutes(30)),
                LocalDateTime.now().plusDays(2).ceilingTimeTo(Duration.ofMinutes(30)),
            )
        )
    }
}