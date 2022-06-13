package org.kvpbldsck.nastolochki.vedro.ui.views.inputs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.kvpbldsck.nastolochki.vedro.R
import org.kvpbldsck.nastolochki.vedro.ui.theme.UiColors
import org.kvpbldsck.nastolochki.vedro.utils.drawUnderline
import org.kvpbldsck.nastolochki.vedro.utils.toPixels

@Composable
fun InputWithPicker(
    value: String?,
    emptyLabelValue: String,
    actionDescription: String,
    showPicker: () -> Unit,
    label: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(TextFieldDefaults.MinHeight)
            .drawUnderline(
                1.dp.toPixels(),
                MaterialTheme.colors.onBackground.copy(alpha = UiColors.TextFieldUnderlineAlpha)
            )
            .clickable { showPicker() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Medium
        )

        Row {
            Text(
                text = value ?: emptyLabelValue,
                modifier = Modifier.alpha(if (value == null) UiColors.TextFieldPlaceholderAlpha else 1f)
            )

            Icon(
                painter = painterResource(R.drawable.icon_right),
                contentDescription = actionDescription,
                modifier = Modifier.alpha(UiColors.TextFieldPlaceholderAlpha)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun InputWithPicker_PreviewDate() {
    DateInput_Preview()
}

@Composable
@Preview(showBackground = true)
fun InputWithPicker_PreviewTime() {
    TimeInput_PreviewEmpty()
}