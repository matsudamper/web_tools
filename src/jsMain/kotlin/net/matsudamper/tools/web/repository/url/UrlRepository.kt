package net.matsudamper.tools.web.repository.url

import com.npmjs.UuidJs
import kotlinx.coroutines.flow.*

class UrlRepository {
    fun insert(): UrlModel {
        return stateFlow.updateAndGet { state ->
            val usedUUIDs = stateFlow.value.map { it.uuid.value }
            val uuid = UrlUUID(UuidJs.generate { it !in usedUUIDs })
            val model = UrlModel(
                uuid = uuid,
                name = "",
                base = "",
            )
            state.plus(model)
        }.last()
    }

    fun update(newValue: UrlModel) {
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

    fun getAll(): StateFlow<List<UrlModel>> {
        return stateFlow.asStateFlow()
    }

    companion object {
        private val stateFlow = MutableStateFlow(listOf<UrlModel>())
    }
}