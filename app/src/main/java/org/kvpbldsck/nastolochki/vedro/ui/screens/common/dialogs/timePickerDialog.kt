package org.kvpbldsck.nastolochki.vedro.ui.screens.common.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import org.kvpbldsck.nastolochki.vedro.R
import org.kvpbldsck.nastolochki.vedro.ui.theme.NastolochkiVedroTheme
import java.time.LocalTime

@Composable
fun timePickerDialog(initialTime: LocalTime, onTimeSelect: (LocalTime) -> Unit): () -> Unit {
    val dialogState = rememberMaterialDialogState()
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton(stringResource(id = R.string.select))
            negativeButton(stringResource(id = R.string.cancel))
        }
    ) {
        timepicker(
            initialTime = initialTime,
            title = stringResource(id = R.string.select_time),
            is24HourClock = true,
            onTimeChange = onTimeSelect
        )
    }

    return dialogState::show
}

@Composable
@Preview(showBackground = true)
fun TimePickerDialog_Preview() {
    NastolochkiVedroTheme {
        timePickerDialog(initialTime = LocalTime.now(), onTimeSelect = {})()
    }
}