package org.kvpbldsck.nastolochki.vedro.ui.screens.events.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.kvpbldsck.nastolochki.vedro.R
import org.kvpbldsck.nastolochki.vedro.models.EventModel
import org.kvpbldsck.nastolochki.vedro.ui.screens.events.models.EventsViewState
import org.kvpbldsck.nastolochki.vedro.ui.screens.events.models.getTestEventsViewState
import org.kvpbldsck.nastolochki.vedro.ui.views.SingleLineCalendar
import org.kvpbldsck.nastolochki.vedro.ui.theme.NastolochkiVedroTheme
import org.kvpbldsck.nastolochki.vedro.ui.views.dialogs.datePickerDialog
import java.time.LocalDate
import java.time.LocalDateTime

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EventsPageView(
    eventsViewState: EventsViewState,
    onDateSelect: (LocalDate) -> Unit,
    onMonthChanged: (String) -> Unit,
    onDateToggled: (EventModel, LocalDateTime, Boolean) -> Unit,
    onVoted: (EventModel, Boolean) -> Unit,
    modifier: Modifier
) {

    val showDatePicker = datePickerDialog(initialDate = eventsViewState.selectedDate, onDateSelect = onDateSelect)

    val scrollableState = rememberScrollState()

    Column(
        modifier = modifier
            .wrapContentHeight()
            .verticalScroll(scrollableState)
            .padding(16.dp, 24.dp)
    ) {
        Row(
            modifier = Modifier.wrapContentSize(),
            verticalAlignment = Alignment.Top
        ) {
            Text(
                style = MaterialTheme.typography.h5,
                text = eventsViewState.currentMonth)

            Spacer(modifier = Modifier.width(12.dp))

            IconButton(
                onClick = { showDatePicker() },
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_calendar),
                        contentDescription = stringResource(id = R.string.open_calendar))
                },
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        SingleLineCalendar(
            selectedDate = eventsViewState.selectedDate,
            currentMonth = eventsViewState.currentMonth,
            onDateSelect = onDateSelect,
            onMonthChanged = onMonthChanged)

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.voting),
            style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            contentPadding = PaddingValues(4.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp))
        {
            items(
                items = eventsViewState.events
                    .filter { !it.isDateSelected }
                    .sortedBy { it.isVoted },
                key = { e -> e.id })
            { event ->
                EventVotingCardView(
                    event = event,
                    onDateToggled = { date, isChecked -> onDateToggled(event, date, isChecked) },
                    onVoted = { onVoted(event, it) },
                    modifier = Modifier.animateItemPlacement())
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.today),
            style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(12.dp))

        for (event in eventsViewState.events.filter { it.isDateSelected && it.selectedDate!!.toLocalDate() == eventsViewState.selectedDate }) {
            EventShortCardView(modifier = Modifier.fillMaxWidth(), event = event)
        }

        Spacer(modifier = Modifier.height(12.dp))
    }

}

@Preview(showBackground = true)
@Composable
fun EventsPageView_Preview() {
    NastolochkiVedroTheme {
        EventsPageView(
            getTestEventsViewState(),
            {},
            {},
            { _, _, _ -> },
            { _, _ -> },
            Modifier.Companion
        )
    }
}