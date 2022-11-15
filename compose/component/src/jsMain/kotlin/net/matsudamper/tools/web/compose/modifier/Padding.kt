package net.matsudamper.tools.web.compose.modifier

import org.jetbrains.compose.web.css.*

public fun StyleScope.padding(
    horizontal: CSSNumeric? = null,
    vertical: CSSNumeric? = null,
) {
    if (vertical != null) {
        paddingBottom(vertical)
        paddingTop(vertical)
    }

    if (horizontal != null) {
        paddingLeft(horizontal)
        paddingRight(horizontal)
    }
}

public fun StyleScope.padding(
    left: CSSNumeric? = null,
    top: CSSNumeric? = null,
    right: CSSNumeric? = null,
    bottom: CSSNumeric? = null,
) {
    if (left != null) {
        paddingLeft(left)
    }
    if (top != null) {
        paddingTop(top)
    }
    if (right != null) {
        paddingRight(right)
    }
    if (bottom != null) {
        paddingBottom(bottom)
    }
}
