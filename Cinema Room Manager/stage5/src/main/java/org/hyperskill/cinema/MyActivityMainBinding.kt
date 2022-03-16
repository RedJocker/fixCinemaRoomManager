package org.hyperskill.cinema

import android.widget.GridLayout
import android.widget.TextView

class MyActivityMainBinding(mainActivity: MainActivity) {
    val cinemaRoomTicketPrice: TextView = mainActivity.findViewById(R.id.cinema_room_ticket_price)
    val cinemaRoomTotalIncome: TextView = mainActivity.findViewById(R.id.cinema_room_total_income)
    val cinemaRoomCurrentIncome: TextView = mainActivity.findViewById(R.id.cinema_room_current_income)
    val cinemaRoomAvailableSeats: TextView = mainActivity.findViewById(R.id.cinema_room_available_seats)
    val cinemaRoomOccupiedSeats:TextView = mainActivity.findViewById(R.id.cinema_room_occupied_seats)
    val cinemaRoomPlaces: GridLayout = mainActivity.findViewById(R.id.cinema_room_places)
}