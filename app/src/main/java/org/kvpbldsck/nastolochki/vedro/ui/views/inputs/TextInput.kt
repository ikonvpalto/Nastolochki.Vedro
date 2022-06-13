package org.kvpbldsck.nastolochki.vedro.ui.views

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.kvpbldsck.nastolochki.vedro.R
import org.kvpbldsck.nastolochki.vedro.ui.theme.NastolochkiVedroTheme
import org.kvpbldsck.nastolochki.vedro.ui.theme.UiColors

@Composable
fun TextInput(
    value: String?,
    onValueChange: (String) -> Unit,
    @StringRes placeholderRes: Int,
    singleLine: Boolean = true,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    val textStyle: TextStyle = LocalTextStyle.current
    val placeholder: @Composable (() -> Unit) = { Text(
        text = stringResource(id = placeholderRes),
        modifier = Modifier.alpha(UiColors.TextFieldPlaceholderAlpha),
        fontWeight = FontWeight.Medium) }
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val shape: Shape = MaterialTheme.shapes.small.copy(bottomEnd = ZeroCornerSize, bottomStart = ZeroCornerSize)
    val colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        backgroundColor = Color.Transparent,
        trailingIconColor = MaterialTheme.colors.contentColorFor(MaterialTheme.colors.background),
        unfocusedIndicatorColor = MaterialTheme.colors.onSurface.copy(alpha = UiColors.TextFieldUnderlineAlpha)
    )
    val textColor = textStyle.color.takeOrElse {
        colors.textColor(true).value
    }
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))

    @OptIn(ExperimentalMaterialApi::class)
    BasicTextField(
        value = value ?: "",
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.backgroundColor(true).value, shape)
            .indicatorLine(true, false, interactionSource, colors)
            .defaultMinSize(
                minWidth = TextFieldDefaults.MinWidth,
                minHeight = TextFieldDefaults.MinHeight
            ),
        onValueChange = onValueChange,
        enabled = true,
        readOnly = false,
        textStyle = mergedTextStyle,
        cursorBrush = SolidColor(colors.cursorColor(false).value),
        visualTransformation = VisualTransformation.None,
        keyboardOptions = KeyboardOptions.Default,
        keyboardActions = KeyboardActions(),
        interactionSource = interactionSource,
        singleLine = singleLine,
        maxLines = Int.MAX_VALUE,
        decorationBox = @Composable { innerTextField ->
            TextFieldDefaults.TextFieldDecorationBox(
                value = value ?: "",
                visualTransformation = VisualTransformation.None,
                innerTextField = innerTextField,
                placeholder = placeholder,
                trailingIcon = trailingIcon,
                singleLine = singleLine,
                enabled = true,
                isError = false,
                interactionSource = interactionSource,
                colors = colors,
                contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(start = 0.dp, end = 0.dp)
            )
        }
    )
}

@Composable
@Preview(showBackground = true)
fun TextInput_PreviewWithoutIcon() {
    NastolochkiVedroTheme {
        TextInput(null, {}, R.string.title)
    }
}

@Composable
@Preview(showBackground = true)
fun TextInput_PreviewWithIcon() {
    NastolochkiVedroTheme {
        TextInput(
            value = null,
            onValueChange = {},
            placeholderRes = R.string.place,
            trailingIcon = { Icon(painter = painterResource(id = R.drawable.icon_map_marker), contentDescription = stringResource(id = R.string.place)) })
    }
}