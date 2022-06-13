package org.kvpbldsck.nastolochki.vedro.ui.views.inputs

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.kvpbldsck.nastolochki.vedro.R
import org.kvpbldsck.nastolochki.vedro.ui.theme.NastolochkiVedroTheme
import org.kvpbldsck.nastolochki.vedro.ui.views.dialogs.dateTimePickerDialog
import org.kvpbldsck.nastolochki.vedro.utils.DateTimeFormats
import org.kvpbldsck.nastolochki.vedro.utils.ceilingTimeTo
import java.time.Duration
import java.time.LocalDateTime

@Composable
fun DateListInputWithPicker(
    selectedDates: List<LocalDateTime>,
    onDateTimeAdded: (date: LocalDateTime) -> Unit,
    onDateTimeRemoved: (index: Int) -> Unit
) {
    val removeDateLabel = stringResource(R.string.remove_date)

    val showDateTimePicker = dateTimePickerDialog(
        initialDateTime = LocalDateTime.now().ceilingTimeTo(Duration.ofMinutes(30)),
        onDateTimeSelect = onDateTimeAdded
    )

    ListInputWithPicker(
        items = selectedDates,
        removeActionDescription = { "$removeDateLabel ${DateTimeFormats.getLongDateAndTimeFormat().format(it)}" },
        addActionDescription = stringResource(R.string.add_variant),
        showAddPickerDialog = showDateTimePicker,
        onItemRemoved = onDateTimeRemoved
    ) { _, dateTime ->
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = DateTimeFormats.getLongDateAndTimeFormat().format(dateTime))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DateListInputWithPicker_PreviewEmpty() {
    NastolochkiVedroTheme {
        DateListInputWithPicker(listOf(), {}, {})
    }
}

@Composable
@Preview(showBackground = true)
fun DateListInputWithPicker_Preview() {
    val dates = listOf(
        LocalDateTime.now().ceilingTimeTo(Duration.ofMinutes(30)),
        LocalDateTime.now().plusDays(1).plusHours(2).ceilingTimeTo(Duration.ofMinutes(30)),
        LocalDateTime.now().plusDays(2).plusHours(1).plusMinutes(30).ceilingTimeTo(Duration.ofMinutes(30)),
    )

    NastolochkiVedroTheme {
        DateListInputWithPicker(dates, {}, {})
    }
}