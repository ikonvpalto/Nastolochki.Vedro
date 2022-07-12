package org.kvpbldsck.nastolochki.vedro.ui.screens.events.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.kvpbldsck.nastolochki.vedro.R
import org.kvpbldsck.nastolochki.vedro.ui.screens.events.models.EventModel
import org.kvpbldsck.nastolochki.vedro.ui.screens.events.models.getTestEventsViewState
import org.kvpbldsck.nastolochki.vedro.ui.theme.NastolochkiVedroTheme
import org.kvpbldsck.nastolochki.vedro.ui.theme.UiColors
import org.kvpbldsck.nastolochki.vedro.ui.screens.common.IconWithText
import org.kvpbldsck.nastolochki.vedro.utils.formatShortTime

@Composable
fun EventShortCard(modifier: Modifier, event: EventModel)
{
    Card(
        elevation = 2.dp,
        shape = MaterialTheme.shapes.large,
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(text = stringResource(R.string.participant), modifier = Modifier.alpha(UiColors.SubtitleTextAlpha))
            Text(text = event.title, style = MaterialTheme.typography.h6)
            Spacer(Modifier.height(8.dp))
            IconWithText(icon = painterResource(id = R.drawable.icon_clock), text = event.selectedDate!!.toLocalTime().formatShortTime())
            IconWithText(icon = painterResource(id = R.drawable.icon_map_marker), text = event.address)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EventShortCard_Preview() {
    NastolochkiVedroTheme {
        EventShortCard(
            Modifier.Companion,
            getTestEventsViewState().events.first { it.isDateSelected }
        )
    }
}