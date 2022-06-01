package org.kvpbldsck.nastolochki.vedro.ui.screens.events.models

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
            participants = listOf(User("Валера"), User("Артем"), User("Ира"), User("Полина"), User("Дикий")),
            possibleDates = (-1L..2L).map{ LocalDateTime.now().plusDays(it) }.toList(),
            votedDates = listOf(),
            isVoted = false
        ),
        EventModel(
            id = UUID.randomUUID(),
            title = "Настолки у Васи",
            address = "ул. Независимости 20",
            participants = listOf(User("Валера"), User("Артем"), User("Ира"), User("Полина"), User("Дикий")),
            possibleDates = (-2L..1L).map{ LocalDateTime.now().plusDays(it) }.toList(),
            votedDates = listOf(),
            isVoted = false
        ),
        EventModel(
            id = UUID.randomUUID(),
            title = "Настолки у Иры",
            address = "ул. Гикало 20",
            participants = listOf(User("Валера"), User("Артем"), User("Ира"), User("Полина"), User("Дикий")),
            possibleDates = (0L..2L).map{ LocalDateTime.now().plusDays(it) }.toList(),
            votedDates = listOf(),
            isVoted = false
        ),
    ))