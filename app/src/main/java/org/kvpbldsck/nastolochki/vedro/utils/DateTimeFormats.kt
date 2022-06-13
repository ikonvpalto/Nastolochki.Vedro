package org.kvpbldsck.nastolochki.vedro.utils

import java.time.format.DateTimeFormatter
import java.util.*

object DateTimeFormats {
    private const val monthName = "LLLL"
    private const val weekdayShort = "EEE"
    private const val dateAndTimeLong = "dd MMMM, HH:mm"
    private const val dateLong = "dd MMMM, $weekdayShort"
    private const val timeShort = "HH:mm"

    fun getMonthFormat(): DateTimeFormatter = DateTimeFormatter.ofPattern(monthName, Locale.getDefault())
    fun getWeekDayFormat(): DateTimeFormatter = DateTimeFormatter.ofPattern(weekdayShort, Locale.getDefault())
    fun getLongDateAndTimeFormat(): DateTimeFormatter = DateTimeFormatter.ofPattern(dateAndTimeLong, Locale.getDefault())
    fun getShortTimeFormat(): DateTimeFormatter = DateTimeFormatter.ofPattern(timeShort, Locale.getDefault())
    fun getDateLongFormat(): DateTimeFormatter = DateTimeFormatter.ofPattern(dateLong, Locale.getDefault())
}
