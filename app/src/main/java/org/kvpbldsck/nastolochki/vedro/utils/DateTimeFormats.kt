package org.kvpbldsck.nastolochki.vedro.utils

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

object DateFormats {
    private const val monthName = "LLLL"
    private const val weekdayShort = "EEE"
    private const val longDateAndTime = "dd MMMM, HH:mm"
    private const val timeShort = "HH:mm"

    fun getMonthFormat(): DateTimeFormatter = DateTimeFormatter.ofPattern(monthName, Locale.getDefault())
    fun getWeekDayFormat(): DateTimeFormatter = DateTimeFormatter.ofPattern(weekdayShort, Locale.getDefault())
    fun getLongDateAndTimeFormat(): DateTimeFormatter = DateTimeFormatter.ofPattern(longDateAndTime, Locale.getDefault())
    fun getShortTimeFormat(): DateTimeFormatter = DateTimeFormatter.ofPattern(timeShort, Locale.getDefault())
}

fun LocalDate.getCapitalizeMonthName(): String = this.getMonthName().capitalize()
fun LocalDate.getMonthName(): String = DateFormats.getMonthFormat().format(this)
fun LocalDate.getWeekDayShortName(): String = DateFormats.getWeekDayFormat().format(this)
fun LocalTime.formatShortTime(): String = DateFormats.getShortTimeFormat().format(this)
