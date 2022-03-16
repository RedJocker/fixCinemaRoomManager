package org.hyperskill.cinema

object TicketPriceCalculator {
    fun calculateBaseTicketPrice(rating : Double, duration: Int, availableSeats: Int) : Double {
        val movieDurationProfit = (-1.0/90.0) * duration * duration + 2.0 * duration + 90.0
        return (rating * movieDurationProfit) / availableSeats
    }
}