package org.hyperskill.cinema


import android.app.AlertDialog
import android.app.Dialog
import android.content.Context



class SeatPurchaseTicketDialog(
        context: Context, seatRow: Int, seatCol: Int, purchaseAction : () -> Unit
) : AlertDialog(context) {

    init {
        val price = TicketPriceCalculator.ticketPrice(seatRow)
        val priceFormatted = "%.2f$".format(price)

        setTitle("Buy a ticket $seatRow row $seatCol place")
        setMessage("Your ticket price is $priceFormatted")
        setButton(Dialog.BUTTON_POSITIVE, "buy a ticket")  { _, _ ->
            purchaseAction()
        }
        setButton(Dialog.BUTTON_NEGATIVE, "cancel purchase") { _, _ ->
//            purchaseAction()
        }
    }
}



