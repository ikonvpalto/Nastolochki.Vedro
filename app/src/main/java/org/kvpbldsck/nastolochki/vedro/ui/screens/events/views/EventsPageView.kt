package org.kvpbldsck.nastolochki.vedro.ui.screens.events.views

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.kvpbldsck.nastolochki.vedro.R
import org.kvpbldsck.nastolochki.vedro.ui.screens.events.models.EventModel
import org.kvpbldsck.nastolochki.vedro.ui.screens.events.models.EventsViewState
import org.kvpbldsck.nastolochki.vedro.ui.screens.events.models.getTestEventsViewState
import org.kvpbldsck.nastolochki.vedro.ui.views.SingleLineCalendar
import org.kvpbldsck.nastolochki.vedro.ui.theme.NastolochkiVedroTheme
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.TemporalField

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EventsPageView(
    eventsViewState: EventsViewState,
    onDateSelect: (LocalDate) -> Unit,
    onMonthChanged: (String) -> Unit,
    onDateToggled: (EventModel, LocalDateTime, Boolean) -> Unit,
    onVoted: (EventModel, Boolean) -> Unit
) {

    val context = LocalContext.current

    val datePickerDialog = DatePickerDialog(
        context,
        { view, year, month, dayOfMonth ->
            onDateSelect(LocalDate.of(year, month, dayOfMonth))
        },
        eventsViewState.selectedDate.year,
        eventsViewState.selectedDate.monthValue - 1,
        eventsViewState.selectedDate.dayOfMonth)

    datePickerDialog.datePicker.minDate = LocalDate.now().minusDays(30).toEpochDay() * 24 * 60 * 60 * 1000
    datePickerDialog.datePicker.maxDate = LocalDate.now().plusDays(60).toEpochDay() * 24 * 60 * 60 * 1000

    Column(Modifier.padding(16.dp, 24.dp)) {
        Row(
            Modifier.wrapContentSize(),
            verticalAlignment = Alignment.Top)
        {
            Text(
                style = MaterialTheme.typography.h6,
                text = eventsViewState.currentMonth)

            Spacer(modifier = Modifier.width(12.dp))

            IconButton(
                onClick = { datePickerDialog.show() },
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_calendar),
                        contentDescription = stringResource(id = R.string.open_calendar))
                },
                modifier = Modifier.size(24.dp)
            )
        }
        Row{
            Spacer(modifier = Modifier.height(8.dp))
        }
        Row {
            SingleLineCalendar(selectedDate = eventsViewState.selectedDate, currentMonth = eventsViewState.currentMonth, onDateSelect = onDateSelect, onMonthChanged = onMonthChanged)
        }
        Column{
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(id = R.string.voting),
                style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(8.dp))
        }
        LazyRow(
            contentPadding = PaddingValues(4.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp))
        {
            items(
                items = eventsViewState.events.sortedBy { it.isVoted },
                key = { e -> e.id })
            { event ->
                EventCardView(
                    event = event,
                    onDateToggled = { date, isChecked -> onDateToggled(event, date, isChecked) },
                    onVoted = { onVoted(event, it) },
                    modifier = Modifier.animateItemPlacement())
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun EventsPageView_Preview() {
    NastolochkiVedroTheme {
        EventsPageView(getTestEventsViewState(), {}, {}, { _, _, _ -> }, { _, _ -> })
    }
}