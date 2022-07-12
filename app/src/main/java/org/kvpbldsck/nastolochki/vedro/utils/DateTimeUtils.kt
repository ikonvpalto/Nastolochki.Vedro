package org.kvpbldsck.nastolochki.vedro.utils

import java.time.LocalDate
import java.time.Month
import java.time.Period

fun getCurrentYearDates(): List<LocalDate> {
    val year = LocalDate.now().year
    val yearStart = LocalDate.of(year, Month.JANUARY, 1)
    val yearEnd = LocalDate.of(year, Month.DECEMBER, 31)

    return (yearStart .. yearEnd step Period.ofDays(1))
        .toList()
}