package org.hyperskill.cinema


import android.view.View
import android.widget.TextView
import android.widget.Toast

object SeatHandler : View.OnClickListener {


    override fun onClick(v: View?) {
        val seat : TextView =  v as TextView? ?: return

        val seatNumber = seat.text.toString()
        val seatRow = seatNumber.substringBefore(".").toIntOrNull() ?: return
        val price = TicketPriceCalculator.calculateTicketPrice(seatRow)
        val priceFormatted = "%.2f$".format(price)
        Toast.makeText(seat.context, priceFormatted, Toast.LENGTH_LONG).show()
    }
}