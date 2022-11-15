package net.matsudamper.tools.web.compose.component

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div

@Composable
public fun Scaffold(
    topAppBar: @Composable () -> Unit = {},
    bottomAppBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    FlexColum(
        attrs = {
            style {
                width(100.percent)
                height(100.percent)
            }
        },
    ) {
        topAppBar()
        Div({
            style {
                flexGrow(1)
                overflow("auto")
            }
        }) {
            content()
        }
        bottomAppBar()
    }
}
