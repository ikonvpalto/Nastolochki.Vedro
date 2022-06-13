package org.kvpbldsck.nastolochki.vedro.ui.views.dialogs

import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import org.kvpbldsck.nastolochki.vedro.R
import org.kvpbldsck.nastolochki.vedro.ui.theme.NastolochkiVedroTheme
import java.time.LocalDate

@Composable
fun datePickerDialog(initialDate: LocalDate, onDateSelect: (LocalDate) -> Unit): () -> Unit {
    val dialogState = rememberMaterialDialogState()
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton(stringResource(id = R.string.select))
            negativeButton(stringResource(id = R.string.cancel))
        }
    ) {
        datepicker(
            initialDate = initialDate,
            title = stringResource(id = R.string.select_date),
            yearRange = IntRange(initialDate.year, initialDate.year),
            onDateChange = onDateSelect
        )
    }

    return  dialogState::show
}

@Composable
@Preview(showBackground = true)
fun DatePickerDialog_Preview() {
    NastolochkiVedroTheme {
        datePickerDialog(initialDate = LocalDate.now(), onDateSelect = {})()
    }
}
