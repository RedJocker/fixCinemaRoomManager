package org.hyperskill.cinema

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.get

object SeatHandler : View.OnClickListener {

    override fun onClick(v: View?) {
        val cardSeat : CardView = v.let{ if(v is CardView) it as CardView else return@onClick }
        val seat : TextView =  if(cardSeat.childCount > 0) {
            cardSeat[0].let { if(it is TextView) it as TextView else return@onClick}
        } else return


        val seatNumber = seat.text.toString()
        val seatRow = seatNumber.substringBefore(".").toIntOrNull() ?: return
        val seatCol = seatNumber.substringAfter(".").toIntOrNull() ?: return

        val purchaseAction: () -> Unit = {
            println("buy seat $seatNumber")
            CinemaState.buyTicket(seatRow)

            cardSeat.post {
                seat.isEnabled = false
                cardSeat.isEnabled = false
                cardSeat.setOnClickListener { }
                cardSeat.setCardBackgroundColor(Color.DKGRAY)
                seat.setBackgroundColor(Color.DKGRAY)
                seat.setTextColor(Color.WHITE)
            }
        }

        SeatPurchaseTicketDialog(cardSeat.context, seatRow, seatCol, purchaseAction).show()
    }
}