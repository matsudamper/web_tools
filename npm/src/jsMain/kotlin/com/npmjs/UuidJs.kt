package com.npmjs

object UuidJs {
    fun generate(): String = UUID.generate()

    fun generate(exclude: (String) -> Boolean): String {
        var uuid = generate()
        while (exclude(uuid).not()) {
            uuid = generate()
        }

        return uuid
    }
}

@JsModule("uuidjs")
@JsNonModule
private external object UUID {
    fun generate(): String
}
