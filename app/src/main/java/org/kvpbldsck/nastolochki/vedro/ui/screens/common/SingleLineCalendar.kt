package org.kvpbldsck.nastolochki.vedro.ui.screens.common

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
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

@Composable
fun rememberSingleLineCalendarState(
    dates: List<LocalDate>,
    listState: LazyListState? = null
) : SingleLineCalendarState {
    return rememberSaveable(saver = SingleLineCalendarState.Saver) {
        SingleLineCalendarState(dates, listState ?: LazyListState())
    }
}

class SingleLineCalendarState(
    val dates: List<LocalDate>,
    val listState: LazyListState = LazyListState()
) : ScrollableState by listState {

    val visibleDates: List<LocalDate>
        get() = listState.layoutInfo.visibleItemsInfo.map { dates[it.index] }

    fun isDateVisible(date: LocalDate) = visibleDates.contains(date)

    suspend fun scrollToDate(date: LocalDate) {
        require(dates.contains(date)) { "Date $date not presented in single line calendar" }

        val index = dates.indexOfFirst { it == date }
        listState.scrollToItem(index - 1)
    }

    suspend fun animateScrollToDate(date: LocalDate) {
        require(dates.contains(date)) { "Date $date not presented in single line calendar" }

        val index = dates.indexOfFirst { it == date }
        listState.animateScrollToItem(index - 1)
    }

    companion object {
        val Saver: Saver<SingleLineCalendarState, *> = mapSaver(
            save = { mapOf(
                "index" to it.listState.firstVisibleItemIndex,
                "offset" to it.listState.firstVisibleItemScrollOffset,
                "dates" to it.dates,
            ) },
            restore = { SingleLineCalendarState(
                dates = it["dates"] as List<LocalDate>,
                listState = LazyListState(
                    firstVisibleItemIndex = it["index"] as Int,
                    firstVisibleItemScrollOffset = it["offset"] as Int
                )
            ) }
        )
    }

}

@Composable
fun SingleLineCalendar(
    selectedDate: LocalDate,
    currentMonth: String,
    state: SingleLineCalendarState,
    onDateSelect: (LocalDate) -> Unit,
    onMonthChanged: (String) -> Unit,
) {

    val uiScope = rememberCoroutineScope()

    val calendarItemModifier = Modifier
        .clip(SingleLineCalendarItemShape)
        .size(35.dp, 60.dp)

    val month = state.visibleDates
            .map { it.getCapitalizeMonthName() }
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
        state = state.listState,
        horizontalArrangement = Arrangement.spacedBy(14.dp)) {

        items(state.dates.size) { index ->

            val date = state.dates[index]

            var buttonModifier = calendarItemModifier

            val item = state.listState.layoutInfo.visibleItemsInfo.firstOrNull { it.index == index }
            if (item != null) {
                buttonModifier = buttonModifier
                        .alpha(animateListCornerElements(item, state.listState.layoutInfo, offset))
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

    LaunchedEffect(state.listState) {
        if (!state.isDateVisible(selectedDate) ) {
            uiScope.launch { state.scrollToDate(selectedDate) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SingleLineCalendar_Preview() {
    NastolochkiVedroTheme {
        SingleLineCalendar(
            LocalDate.now(),
            LocalDate.now().getCapitalizeMonthName(),
            rememberSingleLineCalendarState(getCurrentYearDates()),
            {},
            {})
    }
}