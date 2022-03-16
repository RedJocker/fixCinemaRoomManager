package org.hyperskill.cinema

interface CinemaStateObserver {

    fun onCinemaStateChange(cinemaState: CinemaState)
}