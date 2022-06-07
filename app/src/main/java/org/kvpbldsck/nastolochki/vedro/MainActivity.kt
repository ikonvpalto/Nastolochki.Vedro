package org.kvpbldsck.nastolochki.vedro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.kvpbldsck.nastolochki.vedro.ui.screens.MainScreen
import org.kvpbldsck.nastolochki.vedro.ui.theme.NastolochkiVedroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NastolochkiVedroTheme {
                MainScreen()
            }
        }
    }
}