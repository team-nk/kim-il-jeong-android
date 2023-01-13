package team.nk.kimiljeong.presentation.util

import team.nk.kimiljeong.R
import team.nk.kimiljeong.data.common.Color
import team.nk.kimiljeong.data.extension.toColor

internal fun parseColorToResource(color: String?): Int {
    return when (color?.toColor()) {
        Color.RED -> R.drawable.bg_create_schedule_color_indicator_red_unchecked
        Color.BLUE -> R.drawable.bg_create_schedule_color_indicator_blue_unchecked
        Color.YELLOW -> R.drawable.bg_create_schedule_color_indicator_yellow_unchecked
        Color.PURPLE -> R.drawable.bg_create_schedule_color_indicator_purple_unchecked
        Color.GREEN -> R.drawable.bg_create_schedule_color_indicator_green_unchecked
        Color.ERROR -> R.drawable.img_global_temp
        null -> R.drawable.img_global_temp
    }
}
