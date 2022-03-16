package org.hyperskill.cinema

import android.app.AlertDialog
import android.widget.GridLayout
import android.widget.TextView
import androidx.core.view.forEachIndexed
import org.hyperskill.cinema.abstraction.AbstractUnitTest
import org.hyperskill.cinema.abstraction.find
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

// Version 03.2022
@RunWith(RobolectricTestRunner::class)
class Stage5UnitTest : AbstractUnitTest<MainActivity>(MainActivity::class.java) {


    private val `for total income view` by lazy {
        activity.find<TextView>("cinema_room_total_income")
    }

    private val `for current income view` by lazy {
        activity.find<TextView>("cinema_room_current_income")
    }

    private val `for available seats view` by lazy {
        activity.find<TextView>("cinema_room_available_seats")
    }

    private val `for occupied seats view` by lazy {
        activity.find<TextView>("cinema_room_occupied_seats")
    }

    @Test
    fun `test should check total cinema income value for most profitable movie`() {
        val message = "have you calculated total income properly?"

        activityController.`launch this activity and execute`(arguments = `most profitable movie`()) {
            `for total income view`.`text should`(assertMessage = message) { text ->
                val startsWith = text.startsWith("Total cinema income: ")
                val endsWith = text.endsWith("\$")
                val containsDouble = text.`is contain double`(964.29, 0.1)

                return@`text should` startsWith && endsWith && containsDouble
            }
        }
    }

    @Test
    fun `test should check total cinema income value for default profitable movie`() {
        val message = "have you calculated total income properly?"

        activityController.`launch this activity and execute`(arguments = `default profitable movie`()) {
            `for total income view`.`text should`(assertMessage = message) { text ->
                val startsWith = text.startsWith("Total cinema income: ")
                val endsWith = text.endsWith("\$")
                val containsDouble = text.`is contain double`(850.50, 0.1)
                return@`text should` startsWith && endsWith && containsDouble
            }
        }
    }

    @Test
    fun `test should check initial current cinema income value for most profitable movie`() {
        val message = "have you calculated current income properly?"

        activityController.`launch this activity and execute`(arguments = `most profitable movie`()) {
            `for current income view`.`text should`(assertMessage = message) { text ->
                val startsWith = text.startsWith("Current cinema income: ", ignoreCase = true)
                val endsWith = text.endsWith("\$")
                val containsDouble = text.`is contain double`(0.0, 0.1)
                return@`text should` startsWith && endsWith && containsDouble
            }
        }
    }

    @Test
    fun `test should check initial current cinema income value for default profitable movie`() {
        val message = "have you calculated current income properly?"

        activityController.`launch this activity and execute`(arguments = `default profitable movie`()) {
            `for current income view`.`text should`(assertMessage =  message) { text ->
                val startsWith = text.startsWith("Current cinema income: ", ignoreCase = true)
                val endsWith = text.endsWith("\$")
                val containsDouble = text.`is contain double`(0.0, 0.1)
                return@`text should` startsWith && endsWith && containsDouble
            }
        }
    }

    @Test
    fun `test should check current cinema income value for most profitable movie`() {
        val message = "have you calculated current income properly?"

        activityController.`launch this activity and execute`(arguments = `most profitable movie`()) {
            `grid layout child`(index = 5).`perform click`()
            `in alert dialog`().`for positive button`().`perform click`()
            `for current income view`.`text should`(assertMessage = message) { text ->
                val startsWith = text.startsWith("Current cinema income: ", ignoreCase = true)
                val endsWith = text.endsWith("\$")
                val containsDouble = text.`is contain double`(24.11, 0.1)
                return@`text should` startsWith && endsWith && containsDouble
            }
        }
    }

    @Test
    fun `test should check current cinema income value for default profitable movie`() {
        val message = "have you calculated current income properly?"

        activityController.`launch this activity and execute`(arguments = `default profitable movie`()) {
            `grid layout child`(index = 5).`perform click`()
            `in alert dialog`().`for positive button`().`perform click`()
            `for current income view`.`text should`(assertMessage = message) { text ->
                val startsWith = text.startsWith("Current cinema income: ", ignoreCase = true)
                val endsWith = text.endsWith("\$")
                val containsDouble = text.`is contain double`(21.26, 0.1)
                return@`text should` startsWith && endsWith && containsDouble
            }
        }
    }

    @Test
    fun `test should check initial available seats value`() {
        val message = "have you really counted available seats?"

        activityController.`launch this activity and execute`(arguments = `most profitable movie`()) {
            `for available seats view`.`text should`(assertMessage = message) { text ->
                val startsWith = text.startsWith("Available seats: ")
                val containsInteger = text.`is contain integer`(56)
                return@`text should` startsWith && containsInteger
            }
        }
    }

    @Test
    fun `test should check available seats value`() {
        val message = "have you really counted available seats?"

        activityController.`launch this activity and execute`(arguments = `most profitable movie`()) {
            `grid layout child`(index = 5).`perform click`()
            `in alert dialog`().`for positive button`().`perform click`()
            `for available seats view`.`text should`(assertMessage = message) { text ->
                val startsWithExpectedText = text.startsWith("Available seats: ", ignoreCase = true)
                val containsExpectedInteger = text.`is contain integer`(55)
                println(text)

                return@`text should` startsWithExpectedText && containsExpectedInteger
            }
        }
    }

    @Test
    fun `test should check initial occupied seats value`() {
        val message = "have you really counted occupied seats?"

        activityController.`launch this activity and execute`(arguments = `most profitable movie`()) {
            `for occupied seats view`.`text should`(assertMessage = message) { text ->
                val startsWith = text.startsWith("Occupied seats: ", ignoreCase = true)
                val containsInteger = text.`is contain integer`(0)
                return@`text should` startsWith && containsInteger
            }
        }
    }

    @Test
    fun `test should check occupied seats value`() {
        val message = "have you really counted occupied seats?"

        activityController.`launch this activity and execute`(arguments = `most profitable movie`()) {
            `grid layout child`(index = 5).`perform click`()
            `in alert dialog`().`for positive button`().`perform click`()
            `for occupied seats view`.`text should`(assertMessage = message) { text ->
                val startsWith = text.startsWith("Occupied seats: ", ignoreCase = true)
                val containsInteger = text.`is contain integer`(1)
                return@`text should` startsWith && containsInteger
            }
        }
    }

    @Test
    fun `when seat is clicked twice only first click should count`() {
        val message = "When seat is clicked twice only first click should count"

        lateinit var lastAlertDialog: AlertDialog

        activityController.`launch this activity and execute`(arguments = `most profitable movie`()) {
            repeat(2) {
                `grid layout child`(index = 10).`perform click`()

                if(it == 0) {
                    lastAlertDialog = `in alert dialog`()
                    lastAlertDialog.`for positive button`().`perform click`()
                } else {
                    val currentAlertDialog = `in alert dialog`()
                    currentAlertDialog.`should be same as`(lastAlertDialog)
                }

                `for occupied seats view`.`text should`(assertMessage = message) { text ->
                    val startsWith = text.startsWith("Occupied seats: ", ignoreCase = true)
                    val containsInteger = text.`is contain integer`(1)
                    return@`text should` startsWith && containsInteger
                }
                `for available seats view`.`text should`(assertMessage = message) { text ->
                    val startsWith = text.startsWith("Available seats: ", ignoreCase = true)
                    val containsInteger = text.`is contain integer`(55)
                    return@`text should` startsWith && containsInteger
                }
                `for current income view`.`text should`(assertMessage = message) { text ->
                    val startsWith = text.startsWith("Current cinema income: ", ignoreCase = true)
                    val endsWith = text.endsWith("\$")
                    val containsDouble = text.`is contain double`(21.81, 0.1)
                    return@`text should` startsWith && endsWith && containsDouble
                }
            }
        }
    }

    @Test
    fun `when all seats clicked final state should be displayed`() {
        val clickFailedToOpenNewDialog = "Failed to open new dialog after click on available seat"
        val occupiedSeatCounterError = "Occupied Seats displayed wrong number"
        val availableSeatsCounterError = "Available Seats displayed wrong number"
        val totalIncomeCounterError = "Total Income displayed wrong number"

        var lastAlertDialog: AlertDialog? = null

        val expectedRowValues = listOf(
            24.11, 21.81, 19.52,
            17.22, 14.92, 12.63, 10.33
        ) // these values are rounded, but error margin should be enough to compensate comparing against values with higher precision

        var accumulatedIncome = 0.0

        activityController.`launch this activity and execute`(arguments = `most profitable movie`()) {
            val placesGrid = find<GridLayout>("cinema_room_places")
            placesGrid.forEachIndexed { seatIndex, seatCard ->
                seatCard.`perform click`()
                val currentAlertDialog = `in alert dialog`()

                lastAlertDialog?.also { lastDialog ->
                    currentAlertDialog.`shouldn't be same as`(clickFailedToOpenNewDialog, lastDialog)
                }

                lastAlertDialog = currentAlertDialog
                currentAlertDialog.`for positive button`().`perform click`()

                `for occupied seats view`.`text should`(assertMessage = occupiedSeatCounterError) { expectedText ->
                    val startsWith = expectedText.startsWith("Occupied seats: ", ignoreCase = true)
                    val containsInteger = expectedText.`is contain integer`(1 + seatIndex)
                    return@`text should` startsWith && containsInteger
                }

                `for available seats view`.`text should`(assertMessage = availableSeatsCounterError) { expectedText ->
                    val startsWith = expectedText.startsWith("Available seats: ", ignoreCase = true)
                    val containsInteger = expectedText.`is contain integer`(56  - (seatIndex + 1))
                    return@`text should` startsWith && containsInteger
                }

                accumulatedIncome += expectedRowValues[seatIndex / 8]

                `for current income view`.`text should`(assertMessage = totalIncomeCounterError) { expectedText ->
                    val startsWith = expectedText.startsWith("Current cinema income: ", ignoreCase = true)
                    val endsWith = expectedText.endsWith("\$")
                    val containsDouble = expectedText.`is contain double`(accumulatedIncome, 0.1)
                    return@`text should` startsWith && endsWith && containsDouble
                }
            }
        }
    }
}