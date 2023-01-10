package team.nk.kimiljeong.presentation.util

import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar.LENGTH_SHORT

object ShowSnackBarUtil {

    private fun <V : View> V.showSnackBar(text: String, length: Int) {
        Snackbar.make(
            /* view = */
            this,
            /* text = */
            text,
            /* duration = */
            length,
        ).show()
    }

    fun <V : View> V.showShortSnackBar(text: String) {
        showSnackBar(
            text = text,
            length = LENGTH_SHORT,
        )
    }

    fun <V : View> V.showLongSnackBar(text: String) {
        showSnackBar(
            text = text,
            length = LENGTH_LONG,
        )
    }
}
