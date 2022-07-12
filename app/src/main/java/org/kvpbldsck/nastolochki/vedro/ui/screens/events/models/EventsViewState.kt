package org.kvpbldsck.nastolochki.vedro.ui.screens.events.models

import org.kvpbldsck.nastolochki.vedro.ui.models.UserModel
import org.kvpbldsck.nastolochki.vedro.utils.getCapitalizeMonthName
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class EventsViewState(
    val selectedDate: LocalDate = LocalDate.now(),
    val currentMonth: String = LocalDate.now().getCapitalizeMonthName(),
    val events: List<EventModel> = emptyList()
)

fun getTestEventsViewState() = EventsViewState(
    events = listOf(
        EventModel(
            id = UUID.randomUUID(),
            title = "Настолки у Пети",
            address = "ул. Свободы 20",
            participants = UserModel.getTestUsers(),
            possibleDates = (-1L..2L).map{ LocalDateTime.now().plusDays(it) }.toList(),
            votedDates = listOf(),
            isVoted = false,
            isDateSelected = false,
            selectedDate = null
        ),
        EventModel(
            id = UUID.randomUUID(),
            title = "Настолки у Васи",
            address = "ул. Независимости 20",
            participants = UserModel.getTestUsers(),
            possibleDates = (-2L..1L).map{ LocalDateTime.now().plusDays(it) }.toList(),
            votedDates = listOf(),
            isVoted = false,
            isDateSelected = false,
            selectedDate = null
        ),
        EventModel(
            id = UUID.randomUUID(),
            title = "Настолки у Иры",
            address = "ул. Гикало 20",
            participants = UserModel.getTestUsers(),
            possibleDates = (0L..2L).map{ LocalDateTime.now().plusDays(it) }.toList(),
            votedDates = listOf(),
            isVoted = false,
            isDateSelected = false,
            selectedDate = null
        ),

        EventModel(
            id = UUID.randomUUID(),
            title = "Настолки у Васи",
            address = "ул. Улицы 30",
            participants = UserModel.getTestUsers(),
            possibleDates = listOf(),
            votedDates = listOf(),
            isVoted = false,
            isDateSelected = true,
            selectedDate = LocalDate.now().atTime(19, 0)
        ),
        EventModel(
            id = UUID.randomUUID(),
            title = "Настолки у Пети",
            address = "ул. Свободы 20",
            participants = UserModel.getTestUsers(),
            possibleDates = listOf(),
            votedDates = listOf(),
            isVoted = false,
            isDateSelected = true,
            selectedDate = LocalDate.now().minusDays(1).atTime(20, 0)
        ),
        EventModel(
            id = UUID.randomUUID(),
            title = "Настолки у Васи",
            address = "ул. Независимости 20",
            participants = UserModel.getTestUsers(),
            possibleDates = listOf(),
            votedDates = listOf(),
            isVoted = false,
            isDateSelected = true,
            selectedDate = LocalDate.now().plusDays(1).atTime(18, 30)
        ),
    ))