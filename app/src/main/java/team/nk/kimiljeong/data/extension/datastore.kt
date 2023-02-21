package team.nk.kimiljeong.data.extension

import team.nk.kimiljeong.data.common.Color

fun String.toColor(): Color {
    return when (this) {
        Color.RED.toString() -> Color.RED
        Color.BLUE.toString() -> Color.BLUE
        Color.YELLOW.toString() -> Color.YELLOW
        Color.GREEN.toString() -> Color.GREEN
        Color.PURPLE.toString() -> Color.PURPLE
        else -> {
            Color.ERROR
        }
    }
}
