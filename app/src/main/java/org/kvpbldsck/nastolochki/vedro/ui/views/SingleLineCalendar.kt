package org.kvpbldsck.nastolochki.vedro.ui.views

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.kvpbldsck.nastolochki.vedro.ui.theme.NastolochkiVedroTheme
import org.kvpbldsck.nastolochki.vedro.ui.theme.SingleLineCalendarItemShape
import org.kvpbldsck.nastolochki.vedro.ui.theme.SingleLineCalendarTextStyle
import org.kvpbldsck.nastolochki.vedro.utils.*
import java.time.LocalDate

@Composable
fun SingleLineCalendar(
    selectedDate: LocalDate,
    currentMonth: String,
    onDateSelect: (LocalDate) -> Unit,
    onMonthChanged: (String) -> Unit
) {

    val dates = (-30L..60L)
        .map { daysToAdd -> LocalDate.now().plusDays(daysToAdd)}
        .toList()

    val calendarItemModifier = Modifier
        .clip(SingleLineCalendarItemShape)
        .size(35.dp, 60.dp)
    val selectedCalendarItemModifier = Modifier
        .clip(SingleLineCalendarItemShape)
        .size(35.dp, 60.dp)
        .border(1.5.dp, MaterialTheme.colors.primary, SingleLineCalendarItemShape)
        .padding(0.dp)

    val listState = rememberLazyListState()
    val month = listState.layoutInfo.visibleItemsInfo
            .map { dates[it.index].getCapitalizeMonthName() }
            .groupBy { it }
            .asSequence()
            .sortedByDescending { it.value.size }
            .firstOrNull()
            ?.key
    if (month != null && month != currentMonth) {
        onMonthChanged(month)
    }

    LazyRow(
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(14.dp)) {

        items(dates) { date ->

            val buttonModifier =
                if (date == selectedDate)
                    selectedCalendarItemModifier
                else
                    calendarItemModifier

            TextButton(
                onClick = { onDateSelect(date) },
                modifier = buttonModifier,
                colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.onBackground),
                contentPadding = PaddingValues(0.dp)) {

                Column(
                    modifier = Modifier.size(35.dp, 60.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(style = SingleLineCalendarTextStyle, text = date.getWeekDayShortName())
                    Text(style = SingleLineCalendarTextStyle, text = date.dayOfMonth.toString())
                }

            }
        }
    }

    LaunchedEffect(listState) {
        val index = dates.indexOfFirst { it == selectedDate }
        listState.scrollToItem(index - 1)
    }
}

@Preview(showBackground = true)
@Composable
fun SingleLineCalendar_Preview() {
    NastolochkiVedroTheme {
        SingleLineCalendar(selectedDate = LocalDate.now(), "", {}, {})
    }
}