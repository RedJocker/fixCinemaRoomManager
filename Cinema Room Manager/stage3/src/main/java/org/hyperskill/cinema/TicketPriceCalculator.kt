package org.hyperskill.cinema



object TicketPriceCalculator {
    var baseTicketPrice = 14.22
        private set

    private const val numberOfRows = ROWS
    private const val lowestTicketWeight = 0.5
    private const val highestTicketWeight = 1.5
    private const val differenceOfWeights = highestTicketWeight - lowestTicketWeight
    private const val weightStep = differenceOfWeights / numberOfRows

    private var ticketPrices = listOf<Double>()

    fun setPrices(rating : Double, duration: Int, availableSeats: Int) {
        baseTicketPrice = calculateBaseTicketPrice(rating, duration, availableSeats)
        ticketPrices = calculateTicketPrice()
    }

    fun calculateBaseTicketPrice(rating : Double, duration: Int, availableSeats: Int) : Double {
//        val movieDurationProfit =  -(duration * duration / 90) + 2 * duration + 90
        val movieDurationProfit =  -(duration * duration / 90.0) + 2 * duration + 90
        return (rating * movieDurationProfit) / availableSeats
    }

    fun calculateTicketPrice(row: Int): Double {
        return ticketPrices[row - 1]
    }
    private fun calculateTicketPrice(): List<Double> {
        return (0 until numberOfRows).map{ row ->
            val ticketWeight = highestTicketWeight - (weightStep * row)
            ticketWeight * baseTicketPrice
        }.toList()
    }
}