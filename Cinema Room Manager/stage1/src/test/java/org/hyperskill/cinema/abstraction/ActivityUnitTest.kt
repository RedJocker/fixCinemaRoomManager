package org.hyperskill.cinema.abstraction

import android.app.Activity
import org.robolectric.Robolectric
import org.robolectric.android.controller.ActivityController

/** Class that creates activity controller */
abstract class ActivityUnitTest<T: Activity>(private val activityClass: Class<T>) {

    protected val activityController: ActivityController<T> by lazy {
        Robolectric.buildActivity(activityClass)
    }

    protected val activity: T by lazy {
        activityController.get()
    }
}