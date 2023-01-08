package app.junsu.kimiljeong.data.model.remote.common

import app.junsu.kimiljeong.data.common.Color
import app.junsu.kimiljeong.data.util.toColor
import com.google.gson.annotations.SerializedName

data class PostInformation(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("schedule_content") val scheduleContent: String,
    @SerializedName("address") val address: String,
    @SerializedName("color") private val _color: String,
) {
    val color: Color = _color.toColor()
}
