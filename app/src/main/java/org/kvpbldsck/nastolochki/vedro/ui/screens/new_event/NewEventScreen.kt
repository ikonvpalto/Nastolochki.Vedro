package org.kvpbldsck.nastolochki.vedro.ui.screens.new_event

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.kvpbldsck.nastolochki.vedro.ui.screens.new_event.models.NewEventScreenEvents
import org.kvpbldsck.nastolochki.vedro.ui.screens.new_event.views.NewEventPageView
import org.kvpbldsck.nastolochki.vedro.ui.screens.new_event.views.NewEventTopBar
import org.kvpbldsck.nastolochki.vedro.ui.theme.NastolochkiVedroTheme

@Composable
fun NewEventScreen(newEventViewModel: NewEventViewModel) {

    val scaffoldState = rememberScaffoldState()
    val viewState = newEventViewModel.viewState.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { NewEventTopBar() },
        content = { padding -> NewEventPageView(
            newEventViewState = viewState.value,
            onEventTypeChanged = { newEventViewModel.handleEvent(NewEventScreenEvents.EventTypeChanged(it)) },
            onTitleChanged = { newEventViewModel.handleEvent(NewEventScreenEvents.TitleChanged(it)) },
            onDescriptionChanged = { newEventViewModel.handleEvent(NewEventScreenEvents.DescriptionChanged(it)) },
            onAddressChanged = { newEventViewModel.handleEvent(NewEventScreenEvents.AddressChanged(it)) },
            onDateChanged = { newEventViewModel.handleEvent(NewEventScreenEvents.DateChanged(it)) },
            onTimeChanged = { newEventViewModel.handleEvent(NewEventScreenEvents.TimeChanged(it)) },
            onDateTimeAdded = { newEventViewModel.handleEvent(NewEventScreenEvents.DateTimeAdded(it)) },
            onDateTimeRemoved = { newEventViewModel.handleEvent(NewEventScreenEvents.DateTimeRemoved(it)) },
            onParticipantAdded = { newEventViewModel.handleEvent(NewEventScreenEvents.ParticipantAdded(it)) },
            onParticipantRemoved = { newEventViewModel.handleEvent(NewEventScreenEvents.ParticipantRemoved(it)) },
            onEventCreated = { newEventViewModel.handleEvent(NewEventScreenEvents.EventCreated) },
            modifier = Modifier.padding(padding)
        ) }
    )

}

@Composable
@Preview(showBackground = true)
fun NewEventScreen_Preview() {
    NastolochkiVedroTheme {
        NewEventScreen(NewEventViewModel())
    }
}