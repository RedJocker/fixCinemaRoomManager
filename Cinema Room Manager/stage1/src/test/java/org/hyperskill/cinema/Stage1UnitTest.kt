package org.hyperskill.cinema

import android.widget.GridLayout
import android.widget.TextView
import androidx.core.view.forEachIndexed
import androidx.core.view.size
import org.hyperskill.cinema.abstraction.AbstractUnitTest
import org.hyperskill.cinema.abstraction.find
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

// Version 03.2022
@RunWith(RobolectricTestRunner::class)
class Stage1UnitTest : AbstractUnitTest<MainActivity>(MainActivity::class.java) {

    private val `cinema room places`: GridLayout by lazy {
        activity.find("cinema_room_places")
    }

    private val `for screen text view`: TextView by lazy {
        activity.find("cinema_room_screen_text")
    }

    @Test
    fun `test should check screen view exists`() {
        activityController.`launch this activity and execute` {
            `for screen text view`
        }
    }

    @Test
    fun `test should check screen view text`() {
        val message = "does view with id \"cinema_room_screen_text\" contains a \"Screen\" text?"

        activityController.`launch this activity and execute` {
            `for screen text view`.`text should be`(errorMessage = message, "Screen")
        }
    }

    @Test
    fun `test should check places exists`() {
        activityController.`launch this activity and execute` {
            `cinema room places`
        }
    }

    @Test
    fun `test should check places columns count`() {
        val message = "does view with id \"cinema_room_places\" contains a proper count of columns?"

        activityController.`launch this activity and execute` {
            assertEquals(message, 8, `cinema room places`.columnCount)
        }
    }

    @Test
    fun `test should check places rows count`() {
        val message = "does view with id \"cinema_room_places\" contains a proper count of rows?"

        activityController.`launch this activity and execute` {
            assertEquals(message, 7, `cinema room places`.rowCount)
        }
    }

    @Test
    fun `test should check places seats`() {
        val message = "does view with id \"cinema_room_places\" contains a proper seats describe?"

        activityController.`launch this activity and execute` {
            `cinema room places`.also { gridLayout ->
                assertEquals(56, gridLayout.size)
            }.forEachIndexed { index, seat ->
                val seatRow = index / 8 + 1
                val seatColumn = index % 8 + 1

                // Note: find is necessary because actual TextView may be wrapped inside seat
                val seatText = seat.find<TextView>("cinema_room_place_item_text").text
                assertEquals(message, "${seatRow}.${seatColumn}", seatText)
            }
        }
    }
}