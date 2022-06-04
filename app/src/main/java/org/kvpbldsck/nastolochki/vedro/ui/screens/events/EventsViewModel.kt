package org.kvpbldsck.nastolochki.vedro.ui.screens.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.kvpbldsck.nastolochki.vedro.models.EventModel
import org.kvpbldsck.nastolochki.vedro.ui.screens.events.models.EventsViewState
import org.kvpbldsck.nastolochki.vedro.ui.screens.events.models.getTestEventsViewState
import org.kvpbldsck.nastolochki.vedro.utils.capitalize
import java.time.LocalDate
import java.time.LocalDateTime

class EventsViewModel : ViewModel() {

    private val _viewState: MutableLiveData<EventsViewState> = MutableLiveData(getTestEventsViewState())
    val viewState: LiveData<EventsViewState> = _viewState

    fun onDateSelected(date: LocalDate) {
        _viewState.postValue(_viewState.value!!.copy(selectedDate = date))
    }

    fun onCurrentMonthChanged(month: String) {
        _viewState.postValue(_viewState.value!!.copy(currentMonth = month.capitalize()))
    }

    fun onDateToggled(event: EventModel, date: LocalDateTime, checked: Boolean) {
        val state = viewState.value!!
        _viewState.postValue(state.copy(events = state.events.map {
            if (it == event && checked) {
                it.copy(votedDates = (it.votedDates + date).distinct())
            } else if (it == event) {
                it.copy(votedDates = it.votedDates.filter { d -> d != date })
            } else {
                it.copy()
            }
        }))
    }

    fun onVoteToggle(event: EventModel, isVoted: Boolean) {
        val state = viewState.value!!
        _viewState.postValue(state.copy(events = state.events.map {
            if (it == event) {
                it.copy(isVoted = isVoted)
            } else {
                it.copy()
            }
        }))
    }

}