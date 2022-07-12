package org.kvpbldsck.nastolochki.vedro.ui.screens.new_event.models

import org.kvpbldsck.nastolochki.vedro.ui.models.UserModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

sealed class NewEventScreenEvents {
    data class EventTypeChanged(val type: NewEventTypeEnum) : NewEventScreenEvents()
    data class TitleChanged(val title: String) : NewEventScreenEvents()
    data class DescriptionChanged(val description: String) : NewEventScreenEvents()
    data class AddressChanged(val address: String) : NewEventScreenEvents()
    data class DateChanged(val date: LocalDate) : NewEventScreenEvents()
    data class TimeChanged(val time: LocalTime) : NewEventScreenEvents()
    data class DateTimeAdded(val dateTime: LocalDateTime) : NewEventScreenEvents()
    data class DateTimeRemoved(val index: Int) : NewEventScreenEvents()
    data class ParticipantAdded(val participant: UserModel) : NewEventScreenEvents()
    data class ParticipantRemoved(val index: Int) : NewEventScreenEvents()
    object EventCreated : NewEventScreenEvents()
}