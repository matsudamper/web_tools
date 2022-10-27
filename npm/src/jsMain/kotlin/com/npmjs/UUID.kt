package com.npmjs

@JsModule("uuidjs")
@JsNonModule
external object UUID {
    fun generate(): String
}
