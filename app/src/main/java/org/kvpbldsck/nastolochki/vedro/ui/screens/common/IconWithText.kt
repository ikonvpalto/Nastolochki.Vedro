package org.kvpbldsck.nastolochki.vedro.ui.screens.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.kvpbldsck.nastolochki.vedro.R
import org.kvpbldsck.nastolochki.vedro.ui.theme.NastolochkiVedroTheme

@Composable
fun IconWithText(icon: Painter, text: String) {
    Row {
        Icon(painter = icon, contentDescription = "", tint = MaterialTheme.colors.primary)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun IconWithText_Preview() {
    NastolochkiVedroTheme {
        IconWithText(icon = painterResource(id = R.drawable.icon_clock), text = "19:00")
    }
}