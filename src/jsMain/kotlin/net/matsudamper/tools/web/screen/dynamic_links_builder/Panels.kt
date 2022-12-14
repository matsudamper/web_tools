package screen

import androidx.compose.runtime.*
import net.matsudamper.tools.web.compose.component.Box
import net.matsudamper.tools.web.compose.component.FlexColum
import net.matsudamper.tools.web.compose.component.FlexRow
import net.matsudamper.tools.web.compose.component.ScrollableRow
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLDivElement


@Composable
fun Panels(
    uiStates: List<UrlEditPanelUiState>,
    onClickAdd: () -> Unit,
) {
    val backgroundColor = Color.lightgray
    ScrollableRow({
        style {
            minHeight(400.px)
        }
    }) {
        uiStates.forEach {
            Panel(
                attrs = {
                    style {
                        width(600.px)
                        backgroundColor(backgroundColor)
                        padding(10.px)
                    }
                },
                uiState = it,
            )
            Div({
                style {
                    height(100.percent)
                    width(2.px)
                    backgroundColor(Color.gray)
                }
            })
        }
        var isMouseOver: Boolean by remember {
            mutableStateOf(false)
        }
        Box({
            style {
                width(300.px)
                backgroundColor(backgroundColor)

                height(100.percent)

                if (isMouseOver) {
                    backgroundColor(Color.yellow)
                }
            }
            onClick { onClickAdd() }
            onMouseEnter {
                isMouseOver = true
            }
            onMouseOut {
                isMouseOver = false
            }
        }) {
            Text("Add")
        }
    }
}

@Composable
private fun Panel(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    uiState: UrlEditPanelUiState,
) {
    FlexColum({
        attrs?.invoke(this)
    }) {
        Div({
            style {
                fontSize(1.5.em)
            }
        }) {
            Text(uiState.name)
        }
        uiState.queries.forEach { query ->
            ScrollableRow({
                style {
                    width(100.percent)
                }
            }) {
                Div {
                    TextInput(query.key) {
                        style {
                            fontSize(1.25.em)
                            width(150.px)
                        }
                        onInput {
                            query.onKeyChange(it.value)
                        }
                    }
                }
                Div({
                    style {
                        paddingLeft(0.25.em)
                        paddingRight(0.25.em)
                    }
                }) {
                    Text("=")
                }
                TextArea(query.value) {
                    style {
                        flexGrow(1)
                        fontSize(1.25.em)
                    }
                    onInput {
                        query.onValueChange(it.value)
                    }
                }
            }
        }
        Button({
            style {
                paddingTop(1.em)
                paddingBottom(1.em)
            }
            onClick {
                uiState.onClickAddQuery()
            }
        }) {
            Text("+")
        }
    }
}

data class UrlEditPanelUiState(
    val name: String,
    val base: String,
    val queries: List<Query>,
    val onClickAddQuery: () -> Unit,
) {
    data class Query(
        val key: String,
        val value: String,
        val onKeyChange: (String) -> Unit,
        val onValueChange: (String) -> Unit,
    )
}
