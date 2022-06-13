package org.kvpbldsck.nastolochki.vedro.ui.screens.events.models

import org.kvpbldsck.nastolochki.vedro.models.EventModel
import org.kvpbldsck.nastolochki.vedro.models.User
import org.kvpbldsck.nastolochki.vedro.utils.capitalize
import org.kvpbldsck.nastolochki.vedro.utils.getMonthName
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class EventsViewState(
    val selectedDate: LocalDate,
    val currentMonth: String,
    val events: List<EventModel>
)

fun getTestEventsViewState() = EventsViewState(
    LocalDate.now(),
    LocalDate.now().getMonthName().capitalize(),
    listOf(
        EventModel(
            id = UUID.randomUUID(),
            title = "Настолки у Пети",
            address = "ул. Свободы 20",
            participants = User.getTestUsers(),
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
            participants = User.getTestUsers(),
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
            participants = User.getTestUsers(),
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
            participants = User.getTestUsers(),
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
            participants = User.getTestUsers(),
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
            participants = User.getTestUsers(),
            possibleDates = listOf(),
            votedDates = listOf(),
            isVoted = false,
            isDateSelected = true,
            selectedDate = LocalDate.now().plusDays(1).atTime(18, 30)
        ),
    ))