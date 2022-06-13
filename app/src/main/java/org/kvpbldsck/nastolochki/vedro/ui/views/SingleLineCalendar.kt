package org.kvpbldsck.nastolochki.vedro.ui.views

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.kvpbldsck.nastolochki.vedro.ui.theme.NastolochkiVedroTheme
import org.kvpbldsck.nastolochki.vedro.ui.theme.SingleLineCalendarItemShape
import org.kvpbldsck.nastolochki.vedro.ui.theme.SingleLineCalendarTextStyle
import org.kvpbldsck.nastolochki.vedro.utils.*
import java.time.LocalDate
import java.time.Month
import java.time.Period

@Composable
fun SingleLineCalendar(
    selectedDate: LocalDate,
    currentMonth: String,
    onDateSelect: (LocalDate) -> Unit,
    onMonthChanged: (String) -> Unit
) {

    val uiScope = rememberCoroutineScope()

    val year = LocalDate.now().year
    val yearStart = LocalDate.of(year, Month.JANUARY, 1)
    val yearEnd = LocalDate.of(year, Month.DECEMBER, 31)

    val dates = (yearStart .. yearEnd step Period.ofDays(1))
        .toList()

    val calendarItemModifier = Modifier
        .clip(SingleLineCalendarItemShape)
        .size(35.dp, 60.dp)

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

    val offset = 8.dp.toPixels()

    LazyRow(
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(14.dp)) {

        items(dates.size) { index ->

            val date = dates[index]

            var buttonModifier = calendarItemModifier

            val item = listState.layoutInfo.visibleItemsInfo.firstOrNull { it.index == index }
            if (item != null) {
                buttonModifier = buttonModifier
                        .alpha(animateListCornerElements(item, listState.layoutInfo, offset))
            }

            if (date == selectedDate) {
                buttonModifier = buttonModifier
                    .border(1.5.dp, MaterialTheme.colors.primary, SingleLineCalendarItemShape)
                    .padding(0.dp)
            }

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
        val visibleDates = listState.layoutInfo.visibleItemsInfo
            .map { dates[it.index] }
        if (!visibleDates.contains(selectedDate) ) {
            val index = dates.indexOfFirst { it == selectedDate }
            uiScope.launch { listState.scrollToItem(index - 1) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SingleLineCalendar_Preview() {
    NastolochkiVedroTheme {
        SingleLineCalendar(LocalDate.now(), "", {}, {})
    }
}