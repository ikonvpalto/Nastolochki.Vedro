package org.kvpbldsck.nastolochki.vedro.ui.screens.events

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.kvpbldsck.nastolochki.vedro.R
import org.kvpbldsck.nastolochki.vedro.ui.screens.events.views.EventsPageView
import org.kvpbldsck.nastolochki.vedro.ui.theme.NastolochkiVedroTheme

@Composable
fun EventsScreen(
    eventsViewModel: EventsViewModel = EventsViewModel()
) {

    val scaffoldState = rememberScaffoldState()
    val viewState = eventsViewModel.viewState.observeAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { EventsTopBar() },
        content = { EventsPageView(
            eventsViewState = viewState.value!!,
            onDateSelect = eventsViewModel::onDateSelected,
            onMonthChanged = eventsViewModel::onCurrentMonthChanged,
            onDateToggled = eventsViewModel::onDateToggled,
            onVoted = eventsViewModel::onVoteToggle) }
    )
}

@Composable
private fun EventsTopBar() {
    TopAppBar(
        title = { Text(stringResource(id = R.string.events)) },
    )
}

@Preview(showBackground = true)
@Composable
fun EventsPageScreen_Preview() {
    NastolochkiVedroTheme {
        EventsScreen(EventsViewModel())
    }
}