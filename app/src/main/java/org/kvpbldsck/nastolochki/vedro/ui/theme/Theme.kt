package org.kvpbldsck.nastolochki.vedro.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColorPalette = lightColors(
    primary = UiColors.Orange,
    primaryVariant = UiColors.Orange,
    secondary = UiColors.Orange,
    background = UiColors.White,
    surface = UiColors.White,
    onPrimary = UiColors.White,
    onSecondary = UiColors.White,
    onBackground = UiColors.Black,
    onSurface = UiColors.Black
)

@Composable
fun NastolochkiVedroTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        LightColorPalette
    } else {
        LightColorPalette
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(colors.primary)

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}