package org.hyperskill.cinema

fun Double.asMoney(): String {
    return "%.2f$".format(this)
}