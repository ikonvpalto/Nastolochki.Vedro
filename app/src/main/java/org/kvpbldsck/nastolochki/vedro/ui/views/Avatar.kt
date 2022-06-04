package org.kvpbldsck.nastolochki.vedro.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.kvpbldsck.nastolochki.vedro.models.User
import org.kvpbldsck.nastolochki.vedro.ui.screens.events.models.getTestEventsViewState
import org.kvpbldsck.nastolochki.vedro.ui.theme.NastolochkiVedroTheme
import org.kvpbldsck.nastolochki.vedro.ui.theme.colorsForDefaultAvatar

@Composable
fun Avatar(user: User) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(colorsForDefaultAvatar[user.name.hashCode() % colorsForDefaultAvatar.size])) {

        Text(
            text = user.name.substring(0..0),
            textAlign = TextAlign.Center,
            color = Color.White)

    }
}

@Preview(showBackground = true)
@Composable
fun EventCardView_Preview() {
    NastolochkiVedroTheme {
        Avatar(user = getTestEventsViewState().events.first().participants.first())
    }
}