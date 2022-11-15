package net.matsudamper.tools.web.compose.component

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.ContentBuilder
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLDivElement


@Composable
fun FlexRow(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    reversed: Boolean = false,
    wrap: Boolean = true,
    justifyContent: JustifyContent = JustifyContent.Start,
    alignContent: AlignContent = AlignContent.FlexStart,
    content: ContentBuilder<HTMLDivElement>,
) {
    Div({
        attrs?.invoke(this)
        style {
            display(DisplayStyle.Flex)
            flexFlow(
                flexDirection = if (reversed) {
                    FlexDirection.RowReverse
                } else {
                    FlexDirection.Row
                },
                flexWrap = if (wrap) FlexWrap.Wrap else FlexWrap.Nowrap,
            )
            justifyContent(justifyContent)
            alignContent(alignContent)
        }
    }) {
        content()
    }
}
