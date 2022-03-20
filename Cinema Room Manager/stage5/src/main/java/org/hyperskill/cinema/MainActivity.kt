package org.hyperskill.cinema


import android.os.Bundle
import android.widget.GridLayout
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.core.view.get


class MainActivity : AppCompatActivity(), CinemaStateObserver {

    private val duration by lazy {
        this.intent.extras?.getInt("DURATION", 108) ?: 108
//        this.intent.extras?.getInt("DURATION", 120) ?: 120  // produces "Are default DURATION and RATING properties being used"
    }
    private val rating by lazy {
        (this.intent.extras?.getFloat("RATING", 4.5F) ?: 4.5F).toDouble()
//        (this.intent.extras?.getFloat("RATING", 6.5F) ?: 6.5F).toDouble() // produces "Are default DURATION and RATING properties being used"
    }

    private val binding by lazy {
        MyActivityMainBinding(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CinemaState.initState()

        TicketPriceCalculator.setPrices(rating, duration, TOTAL_SEATS)
//        TicketPriceCalculator.setPrices(rating, duration + 50, TOTAL_SEATS)  // produces "Are DURATION and RATING properties received from intent?"


        runOnUiThread {
            binding.apply {
                cinemaRoomTicketPrice.text =
                        "Estimated ticket price: ${TicketPriceCalculator.baseTicketPrice.asMoney()}"
                cinemaRoomTotalIncome.text =
                        "Total cinema income: ${TicketPriceCalculator.totalCinemaIncome().asMoney()}"
                cinemaRoomCurrentIncome.text = "Current cinema income: ${CinemaState.currentCinemaIncome}$"
                cinemaRoomAvailableSeats.text = "Available seats: ${CinemaState.availableSeats}"
                cinemaRoomOccupiedSeats.text = "Occupied seats: ${CinemaState.occupiedSeats}"

//                findViewById<TextView>(R.id.cinema_room_screen_text).text = "wrong text"  // produces "View with id "cinema_room_screen_text" should contain "Screen" as text. expected:<[Screen]> but was:<[wrong text]>"
//                findViewById<GridLayout>(R.id.cinema_room_places).columnCount = 10  // produces "View with id "cinema_room_places" should contain the correct amount of columns. expected:<8> but was:<10>"
//                findViewById<GridLayout>(R.id.cinema_room_places).rowCount = 10    // produces "View with id "cinema_room_places" should contain the correct amount of rows. expected:<7> but was:<10>"
//                findViewById<GridLayout>(R.id.cinema_room_places).get(0).findViewById<TextView>(R.id.cinema_room_place_item_text).text = "0.1"  //produces "View with id "cinema_room_places" should contain the correct seat number. expected:<[1].1> but was:<[0].1>"
//                cinemaRoomTotalIncome.text = "tTotal cinema income: ${TicketPriceCalculator.totalCinemaIncome().asMoney()}"  //produces "Have you calculated total income properly? expected:<Total cinema income: 964.29$> but was:<tTotal cinema income: 964.29$>"
//                cinemaRoomCurrentIncome.text = "Current cinema income: ${CinemaState.currentCinemaIncome + 20}$"  // produces "Have you calculated current income properly? expected:<Current cinema income: 0.00$> but was:<Current cinema income: 20.0$>"
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

//                cinemaRoomCurrentIncome.text = "Current cinema income: ${(1 + cinemaState.currentCinemaIncome).asMoney()}"  // produces "Have you calculated current income properly? expected:<Current cinema income: 24.11$> but was:<Current cinema income: 25.11$>"
//                cinemaRoomAvailableSeats.text = "Available seats: ${1 + cinemaState.availableSeats}"  // produces "Have you really counted available seats? expected:<Available seats: 55> but was:<Available seats: 56>"
//                cinemaRoomOccupiedSeats.text = "Occupied seats: ${1 + cinemaState.occupiedSeats}"  // produces "Have you really counted occupied seats? expected:<Occupied seats: 1> but was:<Occupied seats: 2>"
            }
        }

    }
}