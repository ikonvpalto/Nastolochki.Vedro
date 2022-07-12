package org.kvpbldsck.nastolochki.vedro.ui.screens.events

import dagger.hilt.android.lifecycle.HiltViewModel
import org.kvpbldsck.nastolochki.vedro.ui.BaseViewModel
import org.kvpbldsck.nastolochki.vedro.ui.screens.events.models.EventsScreenActions
import org.kvpbldsck.nastolochki.vedro.ui.screens.events.models.EventsScreenEvents
import org.kvpbldsck.nastolochki.vedro.ui.screens.events.models.EventsViewState
import org.kvpbldsck.nastolochki.vedro.ui.screens.events.models.getTestEventsViewState
import org.kvpbldsck.nastolochki.vedro.utils.capitalize
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor()
    : BaseViewModel<EventsViewState, EventsScreenEvents, EventsScreenActions>(EventsViewState())
{

    override fun handleEvent(event: EventsScreenEvents) {
        when (event) {
            is EventsScreenEvents.DateSelected -> handleDateSelected(event)
            is EventsScreenEvents.CalendarMonthChanged -> handleCurrentMonthChanged(event)
            is EventsScreenEvents.EventDateToggled -> handleDateToggled(event)
            is EventsScreenEvents.EventVoteToggled -> handleVoteToggle(event)
        }
    }

    private fun handleDateSelected(event: EventsScreenEvents.DateSelected) {
        viewStateValue = viewStateValue.copy(selectedDate = event.date)
        if (event.isScrollingNeeded) {
            viewActionValue = EventsScreenActions.ScrollDateToView(event.date)
        }
    }

    private fun handleCurrentMonthChanged(event: EventsScreenEvents.CalendarMonthChanged) {
        viewStateValue = viewStateValue.copy(currentMonth = event.month.capitalize())
    }

    private fun handleDateToggled(event: EventsScreenEvents.EventDateToggled) {
        viewStateValue = viewStateValue.copy(events = viewStateValue.events.map {
            if (it.id == event.event.id && event.checked) {
                it.copy(votedDates = it.votedDates.plus(event.date))
            } else if (it.id == event.event.id) {
                it.copy(votedDates = it.votedDates.minus(event.date))
            } else {
                it.copy()
            }
        })
    }

    private fun handleVoteToggle(event: EventsScreenEvents.EventVoteToggled) {
        viewStateValue = viewStateValue.copy(events = viewStateValue.events.map {
            if (it.id == event.event.id) {
                it.copy(isVoted = event.isVoted)
            } else {
                it.copy()
            }
        })
    }

    companion object {
        fun getPreviewViewModel() = EventsViewModel().apply { viewStateValue = getTestEventsViewState() }
    }

}