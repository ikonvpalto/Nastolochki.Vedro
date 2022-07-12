package org.kvpbldsck.nastolochki.vedro.ui.screens.new_event

import dagger.hilt.android.lifecycle.HiltViewModel
import org.kvpbldsck.nastolochki.vedro.ui.BaseViewModel
import org.kvpbldsck.nastolochki.vedro.ui.models.UserModel
import org.kvpbldsck.nastolochki.vedro.ui.screens.new_event.models.NewEventScreenActions
import org.kvpbldsck.nastolochki.vedro.ui.screens.new_event.models.NewEventScreenEvents
import org.kvpbldsck.nastolochki.vedro.ui.screens.new_event.models.NewEventTypeEnum
import org.kvpbldsck.nastolochki.vedro.ui.screens.new_event.models.NewEventViewState
import org.kvpbldsck.nastolochki.vedro.utils.minusByIndex
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class NewEventViewModel @Inject constructor() : BaseViewModel<NewEventViewState, NewEventScreenEvents, NewEventScreenActions>(NewEventViewState()) {

    override fun handleEvent(event: NewEventScreenEvents) {
        when (event) {
            is NewEventScreenEvents.EventTypeChanged -> handleEventTypeChanged(event)
            is NewEventScreenEvents.TitleChanged -> handleTitleChanged(event)
            is NewEventScreenEvents.DescriptionChanged -> handleDescriptionChanged(event)
            is NewEventScreenEvents.AddressChanged -> handleAddressChanged(event)
            is NewEventScreenEvents.DateChanged -> handleDateChanged(event)
            is NewEventScreenEvents.TimeChanged -> handleTimeChanged(event)
            is NewEventScreenEvents.DateTimeAdded -> handleDateTimeAdded(event)
            is NewEventScreenEvents.DateTimeRemoved -> handleDateTimeRemoved(event)
            is NewEventScreenEvents.ParticipantAdded -> handleParticipantAdded(event)
            is NewEventScreenEvents.ParticipantRemoved -> handleParticipantRemoved(event)
            is NewEventScreenEvents.EventCreated -> handleEventCreated()
        }
    }

    private fun handleEventTypeChanged(event: NewEventScreenEvents.EventTypeChanged) {
        if (event.type != viewStateValue.type) {
            viewStateValue = viewStateValue.copy(type = event.type)
        }
        else {
            // TODO: add logs
        }
    }

    private fun handleTitleChanged(event: NewEventScreenEvents.TitleChanged) {
        if (viewStateValue.title != event.title) {
            viewStateValue = viewStateValue.copy(title = event.title)
        }
    }

    private fun handleDescriptionChanged(event: NewEventScreenEvents.DescriptionChanged) {
        if (viewStateValue.description != event.description) {
            viewStateValue = viewStateValue.copy(description = event.description)
        }
    }

    private fun handleAddressChanged(event: NewEventScreenEvents.AddressChanged) {
        if (viewStateValue.address != event.address) {
            viewStateValue = viewStateValue.copy(address = event.address)
        }
    }

    private fun handleDateChanged(event: NewEventScreenEvents.DateChanged) {
        if (viewStateValue.type == NewEventTypeEnum.ExactTime
            && viewStateValue.singleDate.date != event.date
        ) {
            viewStateValue = viewStateValue.copy(singleDate = viewStateValue.singleDate.copy(date = event.date))
        }
    }

    private fun handleTimeChanged(event: NewEventScreenEvents.TimeChanged) {
        if (viewStateValue.type == NewEventTypeEnum.ExactTime
            && viewStateValue.singleDate.time != event.time
        ) {
            viewStateValue = viewStateValue.copy(singleDate = viewStateValue.singleDate.copy(time = event.time))
        }
    }

    private fun handleDateTimeAdded(event: NewEventScreenEvents.DateTimeAdded) {
        if (viewStateValue.type == NewEventTypeEnum.WithVoting) {
            if (viewStateValue.possibleDates.contains(event.dateTime)) {
                viewActionValue = NewEventScreenActions.ShowDuplicateDateMessage
            }
            else {
                viewStateValue = viewStateValue.copy(possibleDates = viewStateValue.possibleDates.plus(event.dateTime))
            }
        }
    }

    private fun handleDateTimeRemoved(event: NewEventScreenEvents.DateTimeRemoved) {
        if (viewStateValue.type == NewEventTypeEnum.WithVoting
            && viewStateValue.possibleDates.indices.contains(event.index)
        ) {
            viewStateValue = viewStateValue.copy(possibleDates = viewStateValue.possibleDates.minusByIndex(event.index))
        }
    }

    private fun handleParticipantAdded(event: NewEventScreenEvents.ParticipantAdded) {
        if (!viewStateValue.participants.any { it.name == event.participant.name }) {
            viewStateValue =
                viewStateValue.copy(participants = viewStateValue.participants.plus(event.participant))
        }
    }

    private fun handleParticipantRemoved(event: NewEventScreenEvents.ParticipantRemoved) {
        if (viewStateValue.participants.indices.contains(event.index)) {
            viewStateValue = viewStateValue.copy(
                participants = viewStateValue.participants.minusByIndex(event.index)
            )
        }
    }

    private fun handleEventCreated() {
        TODO("Not yet implemented")
        // val event = map(viewStateValue)
        // repository.save(event)
    }
}
