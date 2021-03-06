package es.iessaladillo.pedrojoya.exchange

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

object SoftInputUtils {
    fun showSoftInput(view: View): Boolean {
        if (view.requestFocus()) {
            val imm = view.context.getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as InputMethodManager
            return imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
        return false
    }

    fun hideSoftKeyboard(view: View): Boolean {
        val imm = view.context.getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        return imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}