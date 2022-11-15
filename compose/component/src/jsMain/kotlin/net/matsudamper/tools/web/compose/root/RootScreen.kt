package net.matsudamper.tools.web.compose.root

import androidx.compose.runtime.Composable
import net.matsudamper.tools.web.compose.ImmutableList
import net.matsudamper.tools.web.compose.component.FlexRow
import net.matsudamper.tools.web.compose.modifier.padding
import net.matsudamper.tools.web.compose.theme.LocalContentColor
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLDivElement

data class RootScreenUiState(
    val items: ImmutableList<Tool>
) {
    data class Tool(
        val name: String,
        val onClick: () -> Unit,
    )
}

@Composable
fun RootScreen(
    uiState: RootScreenUiState
) {
    Div({
        style {
            height(100.percent)
            padding(horizontal = 10.percent)
            overflowY("auto")
        }
    }) {
        FlexRow(
            attrs = {
                style {
                    width(100.percent)
                    minHeight(100.percent)
                    backgroundColor(Color.green)
                }
            },
            justifyContent = JustifyContent.SpaceBetween,
            alignContent = AlignContent.Center,
        ) {
            val attrs: AttrBuilderContext<HTMLDivElement> = {
                style {
                    flexGrow(1f)
                    display(DisplayStyle.Block)
                    padding(horizontal = 2.em, vertical = 2.em)
                    width(15.em)
                    height(15.em)
                }
            }
            (0..10).map {
                ItemContent(
                    attrs = attrs,
                    item = RootScreenUiState.Tool(
                        name = "OK",
                        onClick = {

                        }
                    )
                )
            }
        }
    }
}

@Composable
private fun ItemContent(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    item: RootScreenUiState.Tool
) {
    val textColor = LocalContentColor.current
    Div({
        attrs?.invoke(this)
    }) {
        Div({
            style {
                height(100.percent)
                width(100.percent)
                backgroundColor(Color.aliceblue)
                textAlign("center")
            }
            onClick {
                item.onClick()
            }
        }) {
            Span({
                style {
                    color(textColor)
                }
            }) {
                Text(item.name)
            }
        }
    }
}
