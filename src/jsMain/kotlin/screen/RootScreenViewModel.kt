package screen

import com.npmjs.UuidJs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RootScreenViewModel(
    private val coroutineScope: CoroutineScope,
) {
    private val _uiStateFlow: MutableStateFlow<RootScreenUiState> = MutableStateFlow(
        RootScreenUiState(
            panelsUiStates = listOf(),
            onClickAdd = {
                addUrl()
            },
        )
    )

    private fun addUrl() {
        viewModelStateFlow.update { viewModelState ->
            val usedUUIDs = viewModelState.urlEditPanelUiStates.map { it.uuid }
            val uuid = UuidJs.generate { it !in usedUUIDs }
            viewModelState.copy(
                urlEditPanelUiStates = viewModelStateFlow.value.urlEditPanelUiStates.plus(
                    UrlEditPanelVMState(
                        uuid = uuid,
                        uiState = UrlEditPanelUiState(
                            name = viewModelStateFlow.value.urlEditPanelUiStates.size.toString(),
                            base = "",
                            queries = listOf(),
                            onClickAddQuery = {
                                addQuery(uuid = uuid)
                            }
                        )
                    )
                )
            )
        }
    }

    val uiStateFlow: StateFlow<RootScreenUiState> = _uiStateFlow.asStateFlow()
    private val viewModelStateFlow: MutableStateFlow<VMState> = MutableStateFlow(VMState())

    init {
        coroutineScope.launch {
            viewModelStateFlow.collect { viewModelState ->
                _uiStateFlow.value = _uiStateFlow.value.copy(
                    panelsUiStates = viewModelState.urlEditPanelUiStates.map { it.uiState },
                )
            }
        }
    }

    private fun addQuery(uuid: String) {
        viewModelStateFlow.update { viewModelState ->
            val targetIndex = viewModelState.urlEditPanelUiStates.indexOfFirst { it.uuid == uuid }
                .takeIf { it >= 0 }
                ?: return

            val target = viewModelState.urlEditPanelUiStates[targetIndex]

            val newState = target.copy(
                uiState = target.uiState.copy(
                    queries = target.uiState.queries.plus(
                        UrlEditPanelUiState.Query(
                            key = "",
                            value = "",
                            onKeyChange = {},
                            onValueChange = {},
                        )
                    )
                )
            )

            viewModelState.copy(
                urlEditPanelUiStates = viewModelState.urlEditPanelUiStates.toMutableList().also {
                    it.removeAt(targetIndex)
                    it.add(targetIndex, newState)
                }
            )
        }
    }

    data class VMState(
        val urlEditPanelUiStates: List<UrlEditPanelVMState> = listOf()
    )

    data class UrlEditPanelVMState(
        val uuid: String,
        val uiState: UrlEditPanelUiState,
    )
}
