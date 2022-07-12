package org.kvpbldsck.nastolochki.vedro.ui.screens.events

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.kvpbldsck.nastolochki.vedro.ui.screens.events.models.EventModel
import org.kvpbldsck.nastolochki.vedro.ui.screens.events.models.EventsScreenEvents
import org.kvpbldsck.nastolochki.vedro.ui.screens.events.views.Events
import org.kvpbldsck.nastolochki.vedro.ui.screens.events.views.EventsTopBar
import org.kvpbldsck.nastolochki.vedro.ui.theme.NastolochkiVedroTheme
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun EventsScreen(
    eventsViewModel: EventsViewModel = EventsViewModel()
) {

    val scaffoldState = rememberScaffoldState()
    val viewState = eventsViewModel.viewState.collectAsState()
    val viewAction = eventsViewModel.viewAction.collectAsState(initial = null)

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { EventsTopBar() },
        content = { padding -> Events(
            viewState = viewState.value,
            viewAction = viewAction.value,
            onDateSelect = { date: LocalDate, isScrollingNeeded: Boolean -> eventsViewModel.handleEvent(EventsScreenEvents.DateSelected(date, isScrollingNeeded)) },
            onMonthChanged = { eventsViewModel.handleEvent(EventsScreenEvents.CalendarMonthChanged(it)) },
            onDateToggled = { event: EventModel, date: LocalDateTime, checked: Boolean -> eventsViewModel.handleEvent(EventsScreenEvents.EventDateToggled(event, date, checked)) },
            onVoted = { event: EventModel, toggled: Boolean -> eventsViewModel.handleEvent(EventsScreenEvents.EventVoteToggled(event, toggled)) },
            modifier = Modifier.padding(padding)) }
    )
}

@Preview(showBackground = true)
@Composable
fun EventsPageScreen_Preview() {
    NastolochkiVedroTheme {
        EventsScreen(EventsViewModel.getPreviewViewModel())
    }
}