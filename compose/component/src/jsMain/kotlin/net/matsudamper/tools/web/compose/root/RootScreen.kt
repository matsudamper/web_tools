package net.matsudamper.tools.web.compose.root

import androidx.compose.runtime.Composable
import net.matsudamper.tools.web.compose.component.FlexRow
import net.matsudamper.tools.web.compose.component.Scaffold
import net.matsudamper.tools.web.compose.modifier.padding
import net.matsudamper.tools.web.compose.theme.LocalContentColor
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@Composable
fun RootScreen() {
    Div({
        style {
            padding(horizontal = 10.percent)
        }
    }) {
        FlexRow(
            attrs = {
                style {
                    width(100.percent)
                }
            },
            justifyContent = JustifyContent.SpaceBetween,
        ) {
            val width = 30.percent

            (0..100).map {
                val textColor = LocalContentColor.current
                Div({
                    style {
                        flexGrow(1f)
                        display(DisplayStyle.Block)
                        width(width)
                        backgroundColor(Color.yellow)
                    }
                }) {
                    console.log(textColor)
                    Span({ style { color(textColor) } }) {
                        Text("OK")
                    }
                }
            }
        }
    }
}
