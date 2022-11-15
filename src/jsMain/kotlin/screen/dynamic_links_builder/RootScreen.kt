package screen

import androidx.compose.runtime.*
import net.matsudamper.tools.web.compose.component.FlexColum
import net.matsudamper.tools.web.compose.component.ScrollableRow
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

data class RootScreenUiState(
    val panelsUiStates: List<UrlEditPanelUiState>,
    val onClickAdd: () -> Unit,
)

@Composable
fun DynamicLinkBuilderScreen(
    uiState: RootScreenUiState
) {
    var count: Int by mutableStateOf(0)

    FlexColum({
        style {
            minWidth(100.percent)
            minHeight(100.percent)
        }
    }) {
        ScrollableRow {
            Button(attrs = {
                onClick { count -= 1 }
            }) {
                Text("-")
            }

            Span({ style { padding(15.px) } }) {
                Text("$count")
            }

            Button(attrs = {
                onClick { count += 1 }
            }) {
                Text("+")
            }
        }
        Div({
            style {
                flexGrow(1)
            }
        })
        Panels(
            uiStates = uiState.panelsUiStates,
            onClickAdd = { uiState.onClickAdd() }
        )
    }
}
