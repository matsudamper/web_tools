import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.browser.document
import org.jetbrains.compose.web.renderComposableInBody
import org.w3c.dom.HTMLBodyElement
import org.w3c.dom.HTMLHtmlElement
import org.w3c.dom.get
import screen.RootScreen
import screen.RootScreenViewModel

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
        val coroutineScope = rememberCoroutineScope()
        val viewModel = remember(coroutineScope) {
            RootScreenViewModel(coroutineScope = coroutineScope)
        }

        RootScreen(
            uiState = viewModel.uiStateFlow.collectAsState().value
        )
    }
}
