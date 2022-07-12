package org.kvpbldsck.nastolochki.vedro.ui.screens.new_event.models

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class NewEventDateTime(
    val date: LocalDate? = null,
    val time: LocalTime? = null
) {
    constructor(dateTime: LocalDateTime?) : this(
        date = dateTime?.toLocalDate(),
        time = dateTime?.toLocalTime())

    fun toDateTime(): LocalDateTime? {
        if (date == null || time == null) {
            return null
        }

        return date.atTime(time)
    }
}