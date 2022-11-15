package screen.player

import androidx.compose.runtime.Composable
import kotlinx.browser.document
import org.jetbrains.compose.web.dom.Text

@Composable
internal fun PlayerScreen() {
    Text(document.location!!.pathname)
}