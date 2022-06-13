package org.kvpbldsck.nastolochki.vedro.ui.views.inputs

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.kvpbldsck.nastolochki.vedro.R
import org.kvpbldsck.nastolochki.vedro.ui.theme.NastolochkiVedroTheme
import org.kvpbldsck.nastolochki.vedro.ui.views.dialogs.datePickerDialog
import org.kvpbldsck.nastolochki.vedro.utils.DateTimeFormats
import java.time.LocalDate

@Composable
fun DateInput(
    date: LocalDate?,
    onDateSelect: (LocalDate) -> Unit,
    label: String
) {
    val showDatePickerDialog = datePickerDialog(
        initialDate = date ?: LocalDate.now(),
        onDateSelect = onDateSelect
    )

    InputWithPicker(
        value = if (date == null) null else DateTimeFormats.getDateLongFormat().format(date),
        emptyLabelValue = stringResource(id = R.string.to_select_date),
        actionDescription = stringResource(R.string.to_select_date),
        showPicker = showDatePickerDialog,
        label = label
    )
}


@Composable
@Preview(showBackground = true)
fun DateInput_Preview() {
    NastolochkiVedroTheme {
        DateInput(LocalDate.now(), {}, stringResource(R.string.date))
    }
}

@Composable
@Preview(showBackground = true)
fun DateInput_PreviewEmpty() {
    NastolochkiVedroTheme {
        DateInput(null, {}, stringResource(R.string.date))
    }
}