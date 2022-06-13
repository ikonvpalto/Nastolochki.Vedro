package org.kvpbldsck.nastolochki.vedro.ui.screens.new_event

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.kvpbldsck.nastolochki.vedro.ui.screens.new_event.views.NewEventPageView
import org.kvpbldsck.nastolochki.vedro.ui.screens.new_event.views.NewEventTopBar
import org.kvpbldsck.nastolochki.vedro.ui.theme.NastolochkiVedroTheme

@Composable
fun NewEventScreen(newEventViewModel: NewEventViewModel) {

    val scaffoldState = rememberScaffoldState()
    val viewState = newEventViewModel.viewState.observeAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { NewEventTopBar() },
        content = { padding -> NewEventPageView(
            newEventViewState = viewState.value!!,
            onEventTypeChanged = newEventViewModel::onEventTypeChanged,
            onTitleChanged = newEventViewModel::onTitleChanged,
            onDescriptionChanged = newEventViewModel::onDescriptionChanged,
            onAddressChanged = newEventViewModel::onAddressChanged,
            onDateChanged = newEventViewModel::onDateChanged,
            onTimeChanged = newEventViewModel::onTimeChanged,
            onDateTimeAdded = newEventViewModel::onDateTimeAdded,
            onDateTimeRemoved = newEventViewModel::onDateTimeRemoved,
            onParticipantAdded = newEventViewModel::onParticipantAdded,
            onParticipantRemoved = newEventViewModel::onParticipantRemoved,
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