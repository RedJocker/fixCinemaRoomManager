package org.hyperskill.cinema

import android.app.Dialog
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

//                cardSeat.setCardBackgroundColor(Color.BLUE) //  produces "Make sure you change purchased cinema place indicator via cardBackgroundColor. expected:<-16776961> but was:<-12303292>"
//                cardSeat.setOnClickListener(this)  // produces "A place that was already purchased shouldn't show alert dialog on click. expected:<org.hyperskill.cinema.SeatPurchaseTicketDialog@6a979304> but was:<org.hyperskill.cinema.SeatPurchaseTicketDialog@279bca8e>"
            }
        }

        val seatPurchaseTicketDialog =
            SeatPurchaseTicketDialog(cardSeat.context, seatRow, seatCol, purchaseAction)

        seatPurchaseTicketDialog.show()
//        seatPurchaseTicketDialog.getButton(Dialog.BUTTON_POSITIVE).visibility = View.INVISIBLE  // produces "Expected visibility to be 0 expected:<0> but was:<4>"
//        seatPurchaseTicketDialog.getButton(Dialog.BUTTON_NEGATIVE).visibility = View.INVISIBLE  // produces "Expected visibility to be 0 expected:<0> but was:<4>"
//        seatPurchaseTicketDialog.getButton(Dialog.BUTTON_POSITIVE).text = "not ok"  // produces "Expected text 'Buy a ticket' in MaterialButton expected:<[buy a ticket]> but was:<[not ok]>"
//        seatPurchaseTicketDialog.getButton(Dialog.BUTTON_NEGATIVE).text = "move on"  // produces "Expected text 'Cancel purchase' in MaterialButton expected:<[cancel purchase]> but was:<[move on]>"


    }
}