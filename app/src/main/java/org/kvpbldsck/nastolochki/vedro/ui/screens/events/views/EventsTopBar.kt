package org.kvpbldsck.nastolochki.vedro.ui.screens.events.views

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.kvpbldsck.nastolochki.vedro.R
import org.kvpbldsck.nastolochki.vedro.ui.screens.events.EventsScreen
import org.kvpbldsck.nastolochki.vedro.ui.screens.events.EventsViewModel
import org.kvpbldsck.nastolochki.vedro.ui.theme.NastolochkiVedroTheme

@Composable
fun EventsTopBar() {
    TopAppBar(
        title = { Text(stringResource(id = R.string.events), style = MaterialTheme.typography.h4) },
    )
}

@Preview(showBackground = true)
@Composable
fun EventsTopBar_Preview() {
    NastolochkiVedroTheme {
        EventsTopBar()
    }
}