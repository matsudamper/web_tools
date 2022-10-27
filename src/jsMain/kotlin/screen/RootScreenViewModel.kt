package screen

import com.npmjs.UUID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RootScreenViewModel(
    private val coroutineScope: CoroutineScope,
) {
    private val _uiStateFlow: MutableStateFlow<RootScreenUiState> = MutableStateFlow(
        RootScreenUiState(
            panelsUiStates = listOf(),
            onClickAdd = {
                viewModelStateFlow.value = viewModelStateFlow.value.copy(
                    urlEditPanelUiStates = viewModelStateFlow.value.urlEditPanelUiStates.plus(
                        UrlEditPanelUiState(
                            name = viewModelStateFlow.value.urlEditPanelUiStates.size.toString(),
                            base = "",
                            queries = listOf(),
                            onClickAddQuery = {

                            }
                        )
                    )
                )

                println(
                    UUID.generate()
                )
            },
        )
    )
    val uiStateFlow: StateFlow<RootScreenUiState> = _uiStateFlow.asStateFlow()
    private val viewModelStateFlow: MutableStateFlow<ViewModelState> = MutableStateFlow(ViewModelState())

    init {
        coroutineScope.launch {
            viewModelStateFlow.collect { viewModelState ->
                _uiStateFlow.value = _uiStateFlow.value.copy(
                    panelsUiStates = viewModelState.urlEditPanelUiStates,
                )
            }
        }
    }

    data class ViewModelState(
        val urlEditPanelUiStates: List<UrlEditPanelUiState> = listOf()
    )
}
