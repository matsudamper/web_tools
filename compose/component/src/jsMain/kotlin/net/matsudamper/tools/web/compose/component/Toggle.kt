package net.matsudamper.tools.web.compose.component

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Input
import org.w3c.dom.HTMLInputElement

@Composable
public fun ToggleButton(
    attr: AttrBuilderContext<HTMLInputElement>? = null,
    value: Boolean,
    onChanged: (Boolean) -> Unit,
) {
    Input(
        type = InputType.Checkbox,
        attrs = {
            attr?.invoke(this)
            checked(value)
            onChange {
                onChanged(it.value)
            }
        }
    )
}
