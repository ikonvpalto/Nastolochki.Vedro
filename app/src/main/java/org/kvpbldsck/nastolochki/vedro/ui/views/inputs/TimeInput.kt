package org.kvpbldsck.nastolochki.vedro.ui.views.inputs

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.kvpbldsck.nastolochki.vedro.R
import org.kvpbldsck.nastolochki.vedro.ui.theme.NastolochkiVedroTheme
import org.kvpbldsck.nastolochki.vedro.ui.views.dialogs.timePickerDialog
import org.kvpbldsck.nastolochki.vedro.utils.DateTimeFormats
import org.kvpbldsck.nastolochki.vedro.utils.ceilingTo
import java.time.Duration
import java.time.LocalTime

@Composable
fun TimeInput(
    time: LocalTime?,
    onTimeSelect: (LocalTime) -> Unit,
    label: String
) {
    val showTimePickerDialog = timePickerDialog(
        initialTime = time ?: LocalTime.now().ceilingTo(Duration.ofMinutes(30)),
        onTimeSelect = onTimeSelect
    )

    InputWithPicker(
        value = if (time == null) null else DateTimeFormats.getShortTimeFormat().format(time),
        emptyLabelValue = stringResource(id = R.string.to_select_time),
        actionDescription = stringResource(R.string.to_select_time),
        showPicker = showTimePickerDialog,
        label = label
    )
}


@Composable
@Preview(showBackground = true)
fun TimeInput_Preview() {
    NastolochkiVedroTheme {
        TimeInput(LocalTime.now(), {}, "${stringResource(R.string.start_at)}...")
    }
}

@Composable
@Preview(showBackground = true)
fun TimeInput_PreviewEmpty() {
    NastolochkiVedroTheme {
        TimeInput(null, {}, "${stringResource(R.string.start_at)}...")
    }
}