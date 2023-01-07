package app.junsu.kimiljeong.data.model.remote.common

import app.junsu.kimiljeong.data.model.remote.common.PostInformation.Color.*
import com.google.gson.annotations.SerializedName

data class PostInformation(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("schedule_content") val scheduleContent: String,
    @SerializedName("address") val address: String,
    @SerializedName("color") private val _color: String,
) {
    lateinit var color: Color

    private operator fun invoke() {
        color = _color.toColor()
    }

    private fun String.toColor(): Color {
        return when (this) {
            RED.toString() -> RED
            BLUE.toString() -> BLUE
            YELLOW.toString() -> YELLOW
            GREEN.toString() -> GREEN
            PURPLE.toString() -> PURPLE
            else -> {
                ERROR
            }
        }
    }

    enum class Color {
        RED, BLUE, YELLOW, GREEN, PURPLE, ERROR, ;
    }
}
