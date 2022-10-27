package net.matsudamper.tools.web.compose.component

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.ContentBuilder
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLDivElement

@Composable
fun ScrollableRow(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: ContentBuilder<HTMLDivElement>,
) {
    Div({
        style {
            overflowX("auto")
            display(DisplayStyle.Flex)
        }
    }) {
        Div({
            attrs?.invoke(this)
            style {
                display(DisplayStyle.Flex)
            }
        }) {
            content()
        }
    }
}