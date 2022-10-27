package screen

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import net.matsudamper.tools.web.repository.query.QueryRepository
import net.matsudamper.tools.web.repository.url.UrlRepository

class RootScreenViewModel(
    private val coroutineScope: CoroutineScope,
) {
    private val urlRepository = UrlRepository()
    private val queryRepository = QueryRepository()

    private val _uiStateFlow: MutableStateFlow<RootScreenUiState> = MutableStateFlow(
        RootScreenUiState(
            panelsUiStates = listOf(),
            onClickAdd = {
                urlRepository.insert()
            },
        )
    )

    val uiStateFlow: StateFlow<RootScreenUiState> = _uiStateFlow.asStateFlow()
    private val viewModelStateFlow: MutableStateFlow<VMState> = MutableStateFlow(VMState())

    init {
        coroutineScope.launch {
            combine(
                flows = arrayListOf(
                    urlRepository.getAll(),
                    queryRepository.getAll(),
                )
            ) {
                _uiStateFlow.value = _uiStateFlow.value.copy(
                    panelsUiStates = urlRepository.getAll().value.map { urlModel ->
                        UrlEditPanelUiState(
                            name = urlModel.name,
                            base = urlModel.base,
                            queries = queryRepository.get(urlModel.uuid).map { queryModel ->
                                UrlEditPanelUiState.Query(
                                    key = queryModel.key,
                                    value = queryModel.value,
                                    onKeyChange = {
                                        queryRepository.update(
                                            queryModel.copy(
                                                key = it
                                            )
                                        )
                                    },
                                    onValueChange = {
                                        queryRepository.update(
                                            queryModel.copy(
                                                value = it
                                            )
                                        )
                                    },
                                )
                            },
                            onClickAddQuery = {
                                queryRepository.insert(urlModel.uuid)
                            },
                        )
                    },
                )
            }.collect()
        }
    }

    data class VMState(
        val none: Unit = Unit
    )
}
