package org.hyperskill.cinema

object CinemaState {

    var currentCinemaIncome: Double = 0.0
        private set
    var availableSeats: Int = TOTAL_SEATS
        private set
    var occupiedSeats: Int = 0
        private set
    private val subscribers = mutableListOf<CinemaStateObserver>()

    fun buyTicket(row: Int) {
        currentCinemaIncome += TicketPriceCalculator.ticketPrice(row)
        println("ticket price: ${TicketPriceCalculator.ticketPrice(row)}")
        availableSeats--
        occupiedSeats++
        subscribers.forEach {
            it.onCinemaStateChange(this)
        }
        println(this)
    }

    fun initState() {
        currentCinemaIncome = 0.0
        availableSeats = TOTAL_SEATS
        occupiedSeats = 0
    }

    fun subscribe(subscriber: CinemaStateObserver) {
        subscribers.add(subscriber)
    }

    fun unsubscribe(subscriber: CinemaStateObserver) {
        subscribers.remove(subscriber)
    }

    override fun toString(): String {
        return "currentCinemaIncome: $currentCinemaIncome\n" +
                "availableSeats: $availableSeats\n" +
                "occupiedSeats: $occupiedSeats"
    }
}