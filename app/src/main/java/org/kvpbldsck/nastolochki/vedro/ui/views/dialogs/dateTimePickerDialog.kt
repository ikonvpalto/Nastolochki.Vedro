package org.kvpbldsck.nastolochki.vedro.ui.views.dialogs

import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import org.kvpbldsck.nastolochki.vedro.R
import org.kvpbldsck.nastolochki.vedro.ui.theme.NastolochkiVedroTheme
import java.time.LocalDateTime

@Composable
fun dateTimePickerDialog(initialDateTime: LocalDateTime, onDateTimeSelect: (LocalDateTime) -> Unit): () -> Unit {

    // TODO: change pickers in single same dialog, not by changing dialogs

    var date by remember { mutableStateOf(initialDateTime.toLocalDate()) }

    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()

    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(stringResource(id = R.string.select))
            negativeButton(stringResource(id = R.string.cancel))
        }
    ) {
        datepicker(
            initialDate = date,
            title = stringResource(id = R.string.select_date),
            yearRange = IntRange(initialDateTime.year, initialDateTime.year),
            onDateChange = {
                date = it
                timeDialogState.show()
            }
        )
    }

    MaterialDialog(
        dialogState = timeDialogState,
        buttons = {
            positiveButton(stringResource(id = R.string.select))
            negativeButton(stringResource(id = R.string.cancel))
        }
    ) {
        timepicker(
            initialTime = initialDateTime.toLocalTime(),
            title = stringResource(id = R.string.select_time),
            is24HourClock = true,
            onTimeChange = { onDateTimeSelect(date.atTime(it)) }
        )
    }

    return dateDialogState::show
}

@Composable
@Preview(showBackground = true)
fun DateTimePickerDialog_Preview() {
    NastolochkiVedroTheme {
        dateTimePickerDialog(initialDateTime = LocalDateTime.now(), onDateTimeSelect = {})()
    }
}