package net.matsudamper.tools.web.compose.component

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.overflowX
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.ContentBuilder
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLDivElement

@Composable
fun FlexRow(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: ContentBuilder<HTMLDivElement>,
) {
    Div({
        attrs?.invoke(this)
        style {
            overflowX("auto")
            display(DisplayStyle.Flex)
        }
    }) {
        Div({
            style {
                display(DisplayStyle.Flex)
            }
        }) {
            content()
        }
    }
}
