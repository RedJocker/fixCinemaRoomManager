package org.hyperskill.cinema


import android.os.Bundle
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach

class MainActivity : AppCompatActivity() {

    val duration by lazy {
        this.intent.extras?.getInt("DURATION", 108) ?: 108
    }
    val rating by lazy {
        (this.intent.extras?.getFloat("RATING", 4.5F) ?: 4.5F).toDouble()
    }
    val allSeats by lazy {
        findViewById<GridLayout>(R.id.cinema_room_places)
    }
    val baseTicketPriceView by lazy {
        findViewById<TextView>(R.id.cinema_room_ticket_price)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TicketPriceCalculator.setPrices(rating, duration, TOTAL_SEATS)
        val baseTicketPriceFormatted = "%.2f".format(TicketPriceCalculator.baseTicketPrice)
        baseTicketPriceView.text = "Estimated ticket price: $baseTicketPriceFormatted$"

        initializeListeners()
    }

    private fun initializeListeners() {

        allSeats.forEach { seat ->
            seat.setOnClickListener(SeatHandler)
        }
    }
}