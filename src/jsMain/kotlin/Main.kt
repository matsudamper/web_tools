import androidx.compose.runtime.*
import kotlinx.browser.document
import net.matsudamper.tools.web.compose.component.FlexRow
import net.matsudamper.tools.web.compose.component.Scaffold
import net.matsudamper.tools.web.compose.component.ToggleButton
import net.matsudamper.tools.web.compose.modifier.padding
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposableInBody
import org.w3c.dom.HTMLBodyElement
import org.w3c.dom.HTMLHtmlElement
import org.w3c.dom.get
import screen.DynamicLinkBuilderScreen
import screen.RootScreenViewModel
import net.matsudamper.tools.web.compose.root.RootScreen
import net.matsudamper.tools.web.compose.theme.LocalContentColor
import net.matsudamper.tools.web.compose.theme.LocalSurfaceColor
import net.matsudamper.tools.web.compose.theme.Theme
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Input
import net.matsudamper.tools.web.screen.player.PlayerScreen

fun main() {
    run {
        val html = document.getElementsByTagName("html")[0] as HTMLHtmlElement
        html.style.height = "100%"
        html.style.width = "100%"
    }

    run {
        val body = document.getElementsByTagName("body")[0] as HTMLBodyElement
        body.style.height = "100%"
        body.style.width = "100%"
        body.style.margin = "0"
    }

    renderComposableInBody {
        var isDarkMode by remember { mutableStateOf(true) }
        Theme(isDarkMode = isDarkMode) {
            Scaffold(
                topAppBar = {
                    val textColor = LocalContentColor.current
                    val surfaceColor = LocalSurfaceColor.current
                    FlexRow(
                        attrs = {
                            style {
                                height(50.px)
                                backgroundColor(surfaceColor)
                                property("box-shadow", "1px 1px transparent")
                                color(textColor)
                                padding(horizontal = 1.em)
                            }
                        },
                        alignContent = AlignContent.Center,
                    ) {
                        Text("Web Tools")
                        Div({ style { flexGrow(1) } }) { }
                        Text("isDarkMode")
                        ToggleButton(
                            value = isDarkMode,
                            onChanged = {
                                isDarkMode = it
                            }
                        )
                    }
                }
            ) {
                when (document.location!!.pathname) {
                    "/player/" -> {
                        LaunchedEffect(Unit) {
                            isDarkMode = true
                        }
                        PlayerScreen()
                    }

                    "/dynamic_link_builder/" -> {
                        LaunchedEffect(Unit) {
                            isDarkMode = false
                        }
                        val coroutineScope = rememberCoroutineScope()
                        val viewModel = remember(coroutineScope) {
                            RootScreenViewModel(coroutineScope = coroutineScope)
                        }
                        DynamicLinkBuilderScreen(
                            uiState = viewModel.uiStateFlow.collectAsState().value
                        )
                    }

                    else -> {
                        LaunchedEffect(Unit) {
                            isDarkMode = false
                        }
                        RootScreen()
                    }
                }
            }
        }
    }
}
