package org.kvpbldsck.nastolochki.vedro.ui.screens.new_event.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import org.kvpbldsck.nastolochki.vedro.R
import org.kvpbldsck.nastolochki.vedro.ui.models.UserModel
import org.kvpbldsck.nastolochki.vedro.ui.screens.new_event.models.NewEventTypeEnum
import org.kvpbldsck.nastolochki.vedro.ui.screens.new_event.models.NewEventViewState
import org.kvpbldsck.nastolochki.vedro.ui.theme.NastolochkiVedroTheme
import org.kvpbldsck.nastolochki.vedro.ui.screens.common.TextInput
import org.kvpbldsck.nastolochki.vedro.ui.screens.common.inputs.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NewEventPageView(
    newEventViewState: NewEventViewState,
    onEventTypeChanged: (NewEventTypeEnum) -> Unit,
    onTitleChanged: (String) -> Unit,
    onDescriptionChanged: (String) -> Unit,
    onAddressChanged: (String) -> Unit,
    onDateChanged: (LocalDate) -> Unit,
    onTimeChanged: (LocalTime) -> Unit,
    onDateTimeAdded: (LocalDateTime) -> Unit,
    onDateTimeRemoved: (Int) -> Unit,
    onParticipantAdded: (UserModel) -> Unit,
    onParticipantRemoved: (Int) -> Unit,
    onEventCreated: () -> Unit,
    modifier: Modifier
) {

    val uiScope = rememberCoroutineScope()

    val scrollableState = rememberScrollState()

    val pagerState = rememberPagerState()
    if (pagerState.currentPage != newEventViewState.type.index) {
        onEventTypeChanged(NewEventTypeEnum.values().first { it.index == pagerState.currentPage })
    }

    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Top
    ) {

        Surface(elevation = AppBarDefaults.TopAppBarElevation) {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                backgroundColor = MaterialTheme.colors.primary,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.pagerTabIndicatorOffset(
                            pagerState,
                            tabPositions
                        )
                    )
                }
            ) {
                NewEventTypeEnum.sortedValues().forEach {
                    Tab(
                        selected = it.index == pagerState.currentPage,
                        onClick = { uiScope.launch { pagerState.animateScrollToPage(it.index) } }
                    ) {
                        Text(
                            text = stringResource(id = it.titleRes),
                            modifier = Modifier.padding(vertical = 16.dp),
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }

        HorizontalPager(
            modifier = Modifier
                .wrapContentHeight()
                .verticalScroll(scrollableState)
                .padding(horizontal = 16.dp),
            count = NewEventTypeEnum.values().size,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { tabIndex ->

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                TextInput(
                    value = newEventViewState.title,
                    onValueChange = onTitleChanged,
                    placeholderRes = R.string.title)

                TextInput(
                    value = newEventViewState.description,
                    onValueChange = onDescriptionChanged,
                    placeholderRes = R.string.description)

                TextInput(
                    value = newEventViewState.address,
                    onValueChange = onAddressChanged,
                    placeholderRes = R.string.place,
                    trailingIcon = { Icon(
                        painter = painterResource(id = R.drawable.icon_map_marker),
                        contentDescription = stringResource(id = R.string.place)) })

                when (tabIndex) {
                    NewEventTypeEnum.ExactTime.index -> {
                        DateInput(
                            date = newEventViewState.singleDate.date,
                            onDateSelect = onDateChanged,
                            label = stringResource(R.string.date)
                        )
                        TimeInput(
                            time = newEventViewState.singleDate.time,
                            onTimeSelect = onTimeChanged,
                            label = "${stringResource(R.string.start_at)}..."
                        )
                    }

                    NewEventTypeEnum.WithVoting.index -> {

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "${stringResource(id = R.string.event_happens_at)}:",
                            style = MaterialTheme.typography.h6)

                        Spacer(modifier = Modifier.height(8.dp))

                        DateListInputWithPicker(
                            selectedDates = newEventViewState.possibleDates,
                            onDateTimeAdded = onDateTimeAdded,
                            onDateTimeRemoved = onDateTimeRemoved
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "${stringResource(id = R.string.participants)}:",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.fillMaxWidth())

                Spacer(modifier = Modifier.height(8.dp))

                UserListInputWithPicker(
                    selectedUsers = newEventViewState.participants,
                    availableUsers = UserModel.getTestUsers().minus(newEventViewState.participants.toSet()),
                    onUserAdded = onParticipantAdded,
                    onUserRemoved = onParticipantRemoved
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onEventCreated,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.create_event))
                }

            }

        }

    }
}

@Composable
@Preview(showBackground = true)
fun NewEventPageView_PreviewExactTime() {
    NastolochkiVedroTheme {
        NewEventPageView(
            newEventViewState = NewEventViewState.getTestState(),
            onEventTypeChanged = {},
            onTitleChanged = {},
            onDescriptionChanged = {},
            onAddressChanged = {},
            onDateChanged = {},
            onTimeChanged = {},
            onDateTimeAdded = {},
            onDateTimeRemoved = {},
            onParticipantAdded = {},
            onParticipantRemoved = {},
            onEventCreated = {},
            modifier = Modifier.Companion
        )
    }
}

@Composable
@Preview(showBackground = true)
fun NewEventPageView_PreviewVoting() {
    NastolochkiVedroTheme {
        NewEventPageView(
            newEventViewState = NewEventViewState.getTestState(NewEventTypeEnum.WithVoting),
            onEventTypeChanged = {},
            onTitleChanged = {},
            onDescriptionChanged = {},
            onAddressChanged = {},
            onDateChanged = {},
            onTimeChanged = {},
            onDateTimeAdded = {},
            onDateTimeRemoved = {},
            onParticipantAdded = {},
            onParticipantRemoved = {},
            onEventCreated = {},
            modifier = Modifier.Companion
        )
    }
}