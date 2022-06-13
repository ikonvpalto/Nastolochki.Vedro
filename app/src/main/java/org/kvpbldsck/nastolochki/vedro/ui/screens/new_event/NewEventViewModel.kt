package org.kvpbldsck.nastolochki.vedro.ui.screens.new_event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.kvpbldsck.nastolochki.vedro.models.User
import org.kvpbldsck.nastolochki.vedro.ui.screens.new_event.models.NewEventTypeEnum
import org.kvpbldsck.nastolochki.vedro.ui.screens.new_event.models.NewEventViewState
import org.kvpbldsck.nastolochki.vedro.utils.minus
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class NewEventViewModel : ViewModel() {

    private val _viewState: MutableLiveData<NewEventViewState> = MutableLiveData(NewEventViewState.getTestState())

    val viewState: LiveData<NewEventViewState> = _viewState

    fun onEventTypeChanged(eventType: NewEventTypeEnum) {
        val state = _viewState.value!!
        if (eventType != state.type) {
            _viewState.postValue(state.copy(type = eventType))
        }
        else {
            // TODO: add logs
        }
    }

    fun onTitleChanged(title: String) {
        val state = _viewState.value!!
        if (state.title != title) {
            _viewState.postValue(state.copy(title = title))
        }
    }

    fun onDescriptionChanged(description: String) {
        val state = _viewState.value!!
        if (state.description != description) {
            _viewState.postValue(state.copy(description = description))
        }
    }

    fun onAddressChanged(address: String) {
        val state = _viewState.value!!
        if (state.address != address) {
            _viewState.postValue(state.copy(address = address))
        }
    }

    fun onDateChanged(date: LocalDate) {
        val state = _viewState.value!!
        if (state.type == NewEventTypeEnum.ExactTime) {
            _viewState.postValue(state.copy(singleDate = state.singleDate.copy(date = date)))
        }
    }

    fun onTimeChanged(time: LocalTime) {
        val state = _viewState.value!!
        if (state.type == NewEventTypeEnum.ExactTime) {
            _viewState.postValue(state.copy(singleDate = state.singleDate.copy(time = time)))
        }
    }

    fun onDateTimeAdded(dateTime: LocalDateTime) {
        val state = _viewState.value!!
        if (state.type == NewEventTypeEnum.WithVoting) {
            _viewState.postValue(state.copy(possibleDates = state.possibleDates.plus(dateTime)))
        }
    }

    fun onDateTimeRemoved(indexToRemove: Int) {
        val state = _viewState.value!!
        if (state.type == NewEventTypeEnum.WithVoting) {
            _viewState.postValue(state.copy(possibleDates = state.possibleDates.minus(indexToRemove)))
        }
    }

    fun onParticipantAdded(participant: User) {
        val state = _viewState.value!!
        _viewState.postValue(state.copy(participants = state.participants.plus(participant)))
    }

    fun onParticipantRemoved(indexToRemove: Int) {
        val state = _viewState.value!!
        _viewState.postValue(state.copy(participants = state.participants.minus(indexToRemove)))
    }

}