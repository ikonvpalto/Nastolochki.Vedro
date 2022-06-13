package org.kvpbldsck.nastolochki.vedro.ui.views.inputs

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.kvpbldsck.nastolochki.vedro.R
import org.kvpbldsck.nastolochki.vedro.ui.theme.UiColors
import org.kvpbldsck.nastolochki.vedro.utils.drawUnderline
import org.kvpbldsck.nastolochki.vedro.utils.toPixels

@Composable
fun <T> ListInputWithPicker(
    items: List<T>,
    removeActionDescription: (item: T) -> String,
    addActionDescription: String,
    showAddPickerDialog: () -> Unit,
    onItemRemoved: (index: Int) -> Unit,
    label: @Composable (index: Int, value: T) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {

        items.forEachIndexed { index, item ->
            Row(
                modifier = Modifier
                    .height(TextFieldDefaults.MinHeight)
                    .fillMaxWidth()
                    .drawUnderline(
                        1.dp.toPixels(),
                        MaterialTheme.colors.onBackground.copy(alpha = UiColors.TextFieldUnderlineAlpha)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {
                label(index, item)

                IconButton(onClick = { onItemRemoved(index) }) {
                    Icon(
                        painter = painterResource(R.drawable.icon_close),
                        contentDescription = removeActionDescription(item)
                    )
                }
            }
        }

        TextButton(onClick = showAddPickerDialog) {
            Text(
                text = addActionDescription,
                letterSpacing = 1.sp
            )
        }

    }
}

@Composable
@Preview(showBackground = true)
fun ListInputWithPicker_PreviewDates() {
    DateListInputWithPicker_Preview()
}