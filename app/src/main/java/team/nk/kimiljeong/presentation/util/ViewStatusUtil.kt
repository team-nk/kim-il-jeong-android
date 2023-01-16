package team.nk.kimiljeong.presentation.util

import android.view.View

internal fun View.disable() {
    alpha = 0.4f
    isEnabled = false
}

internal fun View.enable() {
    alpha = 1f
    isEnabled = true
}
