package net.matsudamper.tools.web.compose.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import org.jetbrains.compose.web.css.Color

val LocalContentColor = compositionLocalOf { Color.black }
val LocalSurfaceColor = compositionLocalOf { Color.white }

@Composable
public fun Theme(
    isDarkMode: Boolean = false,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalContentColor provides if (isDarkMode) {
            Color.white
        } else {
            Color.black
        },
        LocalSurfaceColor provides if (isDarkMode) {
            Color("#3c4043")
        } else {
            Color.cadetblue
        },
    ) {
        content()
    }
}