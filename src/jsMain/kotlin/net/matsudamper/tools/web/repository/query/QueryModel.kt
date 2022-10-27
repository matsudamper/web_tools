package net.matsudamper.tools.web.repository.query

import net.matsudamper.tools.web.repository.url.UrlUUID

data class QueryModel(
    val uuid: QueryUUID,
    val urlUuid: UrlUUID,
    val key: String,
    val value: String,
)

value class QueryUUID(val value: String)
