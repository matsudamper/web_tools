package screen

import androidx.compose.runtime.*
import net.matsudamper.tools.web.compose.component.Box
import net.matsudamper.tools.web.compose.component.FlexRow
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.TextInput
import org.w3c.dom.HTMLDivElement


@Composable
fun Panels(
    uiStates: List<UrlEditPanelUiState>,
    onClickAdd: () -> Unit,
) {
    val attr: AttrBuilderContext<HTMLDivElement> = {
        style {
            width(400.px)
            backgroundColor(Color.lightgray)
        }
    }
    FlexRow({
        style {
            minHeight(400.px)
        }
    }) {
        uiStates.forEach {
            Panel(
                attrs = {
                    attr()
                    style {
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
            attr()
            style {
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
    Div({
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
            FlexRow {
                TextInput(query.key) {
                    onInput {
                        query.onValueChange(it.value)
                    }
                }
                Div({
                    style {
                        paddingLeft(0.5.em)
                        paddingRight(0.5.em)
                    }
                }) {
                    Text("=")
                }
                TextInput(query.value) {
                    onInput {
                        query.onValueChange(it.value)
                    }
                }
            }
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
