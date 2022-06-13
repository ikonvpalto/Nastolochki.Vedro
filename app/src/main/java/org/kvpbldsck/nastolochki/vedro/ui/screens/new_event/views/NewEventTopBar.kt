package org.kvpbldsck.nastolochki.vedro.ui.screens.new_event.views

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.kvpbldsck.nastolochki.vedro.R
import org.kvpbldsck.nastolochki.vedro.ui.screens.events.EventsScreen
import org.kvpbldsck.nastolochki.vedro.ui.screens.events.EventsViewModel
import org.kvpbldsck.nastolochki.vedro.ui.theme.NastolochkiVedroTheme

@Composable
fun NewEventTopBar() {
    TopAppBar(
        title = { Text(stringResource(id = R.string.new_event), style = MaterialTheme.typography.h4) },
        elevation = 0.dp
    )
}

@Preview(showBackground = true)
@Composable
fun NewEventTopBar_Preview() {
    NastolochkiVedroTheme {
        NewEventTopBar()
    }
}