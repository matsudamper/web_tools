package net.matsudamper.tools.web.compose

public class ImmutableList<T>(list: List<T>) : List<T> by list.toMutableList()

public fun <T> List<T>.toImmutableList() = ImmutableList(this)
fun <T> immutableListOf(vararg item: T) = ImmutableList(item.toList())
