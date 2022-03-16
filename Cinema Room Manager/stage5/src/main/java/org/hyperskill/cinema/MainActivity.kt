package org.hyperskill.cinema


import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach


class MainActivity : AppCompatActivity(), CinemaStateObserver {

    private val duration by lazy {
        this.intent.extras?.getInt("DURATION", 108) ?: 108
    }
    private val rating by lazy {
        (this.intent.extras?.getFloat("RATING", 4.5F) ?: 4.5F).toDouble()
    }

    private val binding by lazy {
        MyActivityMainBinding(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CinemaState.initState()

        TicketPriceCalculator.setPrices(rating, duration, TOTAL_SEATS)


        runOnUiThread {
            binding.apply {
                cinemaRoomTicketPrice.text =
                        "Estimated ticket price: ${TicketPriceCalculator.baseTicketPrice.asMoney()}"
                cinemaRoomTotalIncome.text =
                        "Total cinema income: ${TicketPriceCalculator.totalCinemaIncome().asMoney()}"
                cinemaRoomCurrentIncome.text = "Current cinema income: ${CinemaState.currentCinemaIncome}$"
                cinemaRoomAvailableSeats.text = "Available seats: ${CinemaState.availableSeats}"
                cinemaRoomOccupiedSeats.text = "Occupied seats: ${CinemaState.occupiedSeats}"
            }

        }

        initializeListeners()
    }

    private fun initializeListeners() {

        CinemaState.subscribe(this)

        binding.cinemaRoomPlaces.forEach { seat ->
            seat.setOnClickListener(SeatHandler)
        }
    }

    override fun onCinemaStateChange(cinemaState: CinemaState) {

        runOnUiThread {
            binding.apply {
                cinemaRoomCurrentIncome.text =
                        "Current cinema income: ${cinemaState.currentCinemaIncome.asMoney()}"
                cinemaRoomAvailableSeats.text = "Available seats: ${cinemaState.availableSeats}"
                cinemaRoomOccupiedSeats.text = "Occupied seats: ${cinemaState.occupiedSeats}"
            }
        }

    }
}