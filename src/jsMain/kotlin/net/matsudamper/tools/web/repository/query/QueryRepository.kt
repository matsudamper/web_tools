package net.matsudamper.tools.web.repository.query

import com.npmjs.UuidJs
import kotlinx.coroutines.flow.*
import net.matsudamper.tools.web.repository.url.UrlModel
import net.matsudamper.tools.web.repository.url.UrlUUID

class QueryRepository {
    fun insert(urlUuid: UrlUUID): QueryModel {
        return stateFlow.updateAndGet { state ->
            val usedUUIDs = stateFlow.value.map { it.uuid.value }
            val uuid = QueryUUID(UuidJs.generate { it !in usedUUIDs })
            val model = QueryModel(
                uuid = uuid,
                urlUuid = urlUuid,
                key = "",
                value = "",
            )
            state.plus(model)
        }.last()
    }

    fun update(newValue: QueryModel) {
        stateFlow.update { state ->
            val index = state.indexOfFirst { it.uuid == newValue.uuid }
                .takeIf { it >= 0 }
                ?: return

            state.toMutableList().also {
                it.removeAt(index)
                it.add(index, newValue)
            }
        }
    }

    fun get(urlUuid: UrlUUID): List<QueryModel> {
        return stateFlow.value.filter { it.urlUuid == urlUuid }
    }

    fun getAll(): StateFlow<List<QueryModel>> {
        return stateFlow.asStateFlow()
    }

    companion object {
        private val stateFlow = MutableStateFlow(listOf<QueryModel>())
    }
}
