package org.kvpbldsck.nastolochki.vedro.utils

import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import java.time.Period
import java.time.temporal.Temporal
import java.time.temporal.TemporalAmount

sealed class TemporalIterator<T, TA> (
    private val start: T,
    private val endInclusive: T,
    private val step: TA
): Iterator<T> where T: Temporal, T: Comparable<T>, TA: TemporalAmount  {

    class TimeIterator(start: LocalTime, endInclusive: LocalTime, step: Duration): TemporalIterator<LocalTime, Duration>(start, endInclusive, step)
    class DateIterator(start: LocalDate, endInclusive: LocalDate, step: Period): TemporalIterator<LocalDate, Period>(start, endInclusive, step)

    private var currentState = start

    override fun hasNext() = (currentState.plus(step) as T) <= endInclusive

    override fun next(): T {
        val next = currentState
        currentState = currentState.plus(step) as T
        return next
    }
}

class TimeProgression(
    override val start: LocalTime,
    override val endInclusive: LocalTime,
    private val step: Duration = Duration.ofSeconds(1)
) : Iterable<LocalTime>, ClosedRange<LocalTime> {

    override fun iterator(): Iterator<LocalTime> =
        TemporalIterator.TimeIterator(start, endInclusive, step)

    infix fun step(step: Duration) = TimeProgression(start, endInclusive, step)
    infix fun step(minutes: Long) = TimeProgression(start, endInclusive, Duration.ofMinutes(minutes))

}

operator fun LocalTime.rangeTo(other: LocalTime) = TimeProgression(this, other)

class DateProgression(
    override val start: LocalDate,
    override val endInclusive: LocalDate,
    private val step: Period = Period.ofDays(1)
) : Iterable<LocalDate>, ClosedRange<LocalDate> {

    override fun iterator(): Iterator<LocalDate> =
        TemporalIterator.DateIterator(start, endInclusive, step)

    infix fun step(step: Period) = DateProgression(start, endInclusive, step)
    infix fun step(days: Int) = DateProgression(start, endInclusive, Period.ofDays(days))

}

operator fun LocalDate.rangeTo(other: LocalDate) = DateProgression(this, other)