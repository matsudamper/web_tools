package net.matsudamper.tools.web.repository.url

data class UrlModel(
    val uuid: UrlUUID,
    val name: String,
    val base: String,
)

value class UrlUUID(val value: String)
