package org.hyperskill.cinema


import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.View


class SeatPurchaseTicketDialog(
        context: Context, seatRow: Int, seatCol: Int, purchaseAction : () -> Unit
) : AlertDialog(context) {

    init {
        val price = TicketPriceCalculator.ticketPrice(seatRow)


        setTitle("Buy a ticket $seatRow row $seatCol place")
//        setTitle("Buy a ticket ${seatRow + 1} row $seatCol place")  // produces "Make sure you properly pass row number and place number into dialog. expected:<Buy a ticket [1] row 6 place> but was:<Buy a ticket [2] row 6 place>"
        setMessage("Your ticket price is ${price.asMoney()}")
//        setMessage("yYour ticket price is ${price.asMoney()}")       // produces "Make sure you properly pass ticket price into dialog. expected:<Your ticket price is 24.11$> actual:<yYour ticket price is 24.11$>"
        setButton(Dialog.BUTTON_POSITIVE, "buy a ticket")  { _, _ ->
            purchaseAction()
        }

        setButton(Dialog.BUTTON_NEGATIVE, "cancel purchase") { _, _ -> }
//        setButton(Dialog.BUTTON_NEGATIVE, "cancel purchase") { _, _ -> purchaseAction() } // produces both "You should do nothing if the purchase was canceled (Indicator color and Indication color should not be equal). Actual: -12303292" and  "Alert dialog should be displayed again if the previous purchase was canceled (dialogs should be different). Actual: org.hyperskill.cinema.SeatPurchaseTicketDialog@1c48dcef"
    }
}



