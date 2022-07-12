package org.kvpbldsck.nastolochki.vedro.utils

import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

fun LocalDate.getCapitalizeMonthName(): String = this.getMonthName().capitalize()
private fun LocalDate.getMonthName(): String = DateTimeFormats.getMonthFormat().format(this)
fun LocalDate.getWeekDayShortName(): String = DateTimeFormats.getWeekDayFormat().format(this)
fun LocalTime.formatShortTime(): String = DateTimeFormats.getShortTimeFormat().format(this)

fun LocalTime.ceilingTo(step: Duration): LocalTime {
    val timeInNano = this.toNanoOfDay()
    val stepInNano = step.toNanos()

    if (step > Duration.ofDays(1)) {
        throw IllegalArgumentException("Step should be less than or equal to day")
    }

    var resultInNano =
        if (timeInNano % stepInNano == 0L)
            timeInNano
        else
            timeInNano + stepInNano - (timeInNano % stepInNano)
    resultInNano %= Duration.ofDays(1).toNanos()

    return LocalTime.ofNanoOfDay(resultInNano)
}

fun LocalDateTime.ceilingTimeTo(step: Duration): LocalDateTime {
    val timeInNano = this.toLocalTime().toNanoOfDay()
    val stepInNano = step.toNanos()

    if (step > Duration.ofDays(1)) {
        throw IllegalArgumentException("Step should be less than or equal to day")
    }

    var resultInNano =
        if (timeInNano % stepInNano == 0L)
            timeInNano
        else
            timeInNano + stepInNano - (timeInNano % stepInNano)
    resultInNano %= Duration.ofDays(1).toNanos()

    return this.toLocalDate().atTime(LocalTime.ofNanoOfDay(resultInNano))
}