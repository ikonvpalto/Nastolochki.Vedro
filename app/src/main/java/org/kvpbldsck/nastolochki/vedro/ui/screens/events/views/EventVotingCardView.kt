package org.kvpbldsck.nastolochki.vedro.ui.screens.events.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.kvpbldsck.nastolochki.vedro.R
import org.kvpbldsck.nastolochki.vedro.models.EventModel
import org.kvpbldsck.nastolochki.vedro.ui.screens.events.models.getTestEventsViewState
import org.kvpbldsck.nastolochki.vedro.ui.theme.NastolochkiVedroTheme
import org.kvpbldsck.nastolochki.vedro.ui.views.Avatar
import org.kvpbldsck.nastolochki.vedro.ui.views.IconWithText
import org.kvpbldsck.nastolochki.vedro.utils.DateFormats
import java.time.LocalDateTime

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun EventVotingCardView(
    event: EventModel,
    onDateToggled: (LocalDateTime, Boolean) -> Unit,
    onVoted: (Boolean) -> Unit,
    modifier: Modifier
)
{
    Surface(
        elevation = 4.dp,
        shape = MaterialTheme.shapes.large,
        modifier = modifier)
    {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(text = stringResource(R.string.participant), modifier = Modifier.alpha(0.8f))

            Text(text = event.title, style = MaterialTheme.typography.h6)

            Spacer(Modifier.height(10.dp))

            IconWithText(icon = painterResource(id = R.drawable.icon_map_marker), text = event.address)

            Spacer(Modifier.height(10.dp))

            Text(text = "${stringResource(id = R.string.participants)}:")
            
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(top = 4.dp)) {
                for(participant in event.participants) {
                    Avatar(user = participant)
                }
            }

            Spacer(Modifier.height(16.dp))
            
            Text(text = "${stringResource(id = R.string.convenient_time)}:")
            
            for (dateVariant in event.possibleDates) {
                Row(
                    modifier = Modifier
                        .padding(0.dp)
                        .clickable(role = Role.Checkbox) { onDateToggled(dateVariant, !event.votedDates.contains(dateVariant)) },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement.provides(false)) {
                        Checkbox(
                            modifier = Modifier
                                .padding(top = 4.dp, end = 8.dp, bottom = 4.dp)
                                .size(24.dp),
                            checked = event.votedDates.contains(dateVariant),
                            onCheckedChange = { onDateToggled(dateVariant, it) },
                            enabled = !event.isVoted)
                        Text(text = DateFormats.getLongDateAndTimeFormat().format(dateVariant))
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Button(
                enabled = event.isVoted || event.votedDates.any(),
                onClick = { onVoted(!event.isVoted) })
            {
                Text(text = if (event.isVoted) stringResource(id = R.string.retract_vote) else stringResource(id = R.string.vote))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EventVotingCardView_Preview() {
    NastolochkiVedroTheme {
        EventVotingCardView(
            getTestEventsViewState().events.first(),
            { _, _ -> },
            {},
            Modifier.Companion
        )
    }
}