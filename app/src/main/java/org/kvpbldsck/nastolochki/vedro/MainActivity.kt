package org.kvpbldsck.nastolochki.vedro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import org.kvpbldsck.nastolochki.vedro.ui.screens.events.EventsScreen
import org.kvpbldsck.nastolochki.vedro.ui.theme.NastolochkiVedroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NastolochkiVedroTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    EventsScreen()
                }
            }
        }
    }
}