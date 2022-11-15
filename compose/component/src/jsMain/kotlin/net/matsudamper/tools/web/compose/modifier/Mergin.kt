package net.matsudamper.tools.web.compose.modifier

import org.jetbrains.compose.web.css.*

public fun StyleScope.margin(
    horizontal: CSSNumeric? = null,
    vertical: CSSNumeric? = null,
) {
    if (vertical != null) {
        marginBottom(vertical)
        marginTop(vertical)
    }

    if (horizontal != null) {
        marginLeft(horizontal)
        marginRight(horizontal)
    }
}

public fun StyleScope.margin(
    left: CSSNumeric? = null,
    top: CSSNumeric? = null,
    right: CSSNumeric? = null,
    bottom: CSSNumeric? = null,
) {
    if (left != null) {
        marginLeft(left)
    }
    if (top != null) {
        marginTop(top)
    }
    if (right != null) {
        marginRight(right)
    }
    if (bottom != null) {
        marginBottom(bottom)
    }
}
