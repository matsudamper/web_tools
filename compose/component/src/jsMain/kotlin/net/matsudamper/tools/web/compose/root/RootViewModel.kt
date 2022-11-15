package net.matsudamper.tools.web.compose.root

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import net.matsudamper.tools.web.compose.immutableListOf

class RootViewModel {
    val uiStateFlow: StateFlow<RootScreenUiState> = MutableStateFlow(
        RootScreenUiState(
            items = immutableListOf(),
        )
    ).also { mutableUiStateFlow ->

    }
}