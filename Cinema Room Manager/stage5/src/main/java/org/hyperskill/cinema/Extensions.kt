package org.hyperskill.cinema

fun Double.asMoney(): String {
//    return "%.3f$".format(this)  // produces "Make sure you have correctly formatted the ticket price message. The price should contain two numbers after the dot./nexpected:<Estimated ticket price: [priceWithTwoDecimals]$> but was:<Estimated ticket price: 10.523$>
    return "%.2f$".format(this)
}