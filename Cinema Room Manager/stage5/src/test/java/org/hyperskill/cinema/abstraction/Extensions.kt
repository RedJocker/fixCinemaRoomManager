package org.hyperskill.cinema.abstraction

import android.app.AlertDialog
import android.content.Context
import android.view.View
import org.hyperskill.cinema.MainActivity
import org.junit.Assert


    fun Context.identifier(id: String, `package`: String = packageName): Int {
        return resources.getIdentifier(id, "id", `package`)
    }

    inline fun <reified T : View> View.find(id: String): T {
        val maybeView: View? = findViewById(context.identifier(id))

        val idNotFoundMessage = "View with id \"$id\" was not found"
        val wrongClassMessage = "View with id \"$id\" is not from expected class. " +
                "Expected ${T::class.java.simpleName} found ${maybeView?.javaClass?.simpleName}"

        Assert.assertNotNull(idNotFoundMessage, maybeView)
        Assert.assertTrue(wrongClassMessage, maybeView is T)
        return maybeView as T
    }

    inline fun <reified T : View> MainActivity.find(id: String): T {

        val maybeView: View? = findViewById(identifier(id))

        val idNotFoundMessage = "View with id \"$id\" was not found"
        val wrongClassMessage = "View with id \"$id\" is not from expected class. " +
                "Expected ${T::class.java.simpleName} found ${maybeView?.javaClass?.simpleName}"

        Assert.assertNotNull(idNotFoundMessage, maybeView)
        Assert.assertTrue(wrongClassMessage, maybeView is T)
        return maybeView as T
    }

    inline fun <reified T : View> MainActivity.findOrNull(id: String): T? {
        val maybeView: View? = findViewById(identifier(id))

        val wrongClassMessage = "View with id \"$id\" is not from expected class. " +
                "Expected ${T::class.java.simpleName} found ${maybeView?.javaClass?.simpleName}"

        Assert.assertTrue(wrongClassMessage, maybeView is T?)
        return maybeView as T?
    }

    inline fun <reified T : View> AlertDialog.find(id: String, `package`: String = context.packageName): T {
        val maybeView: View? = findViewById(context.identifier(id, `package`))

        val idNotFoundMessage = "View with id \"$id\" was not found"
        val wrongClassMessage = "View with id \"$id\" is not from expected class. " +
                "Expected ${T::class.java.simpleName} found ${maybeView?.javaClass?.simpleName}"

        Assert.assertNotNull(idNotFoundMessage, maybeView)
        Assert.assertTrue(wrongClassMessage, maybeView is T)
        return maybeView as T
}
