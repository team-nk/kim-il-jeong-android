package app.junsu.kimiljeong.data.model.remote.common

import app.junsu.kimiljeong.data.TypeCastingUtil.toColor
import app.junsu.kimiljeong.data.common.Color
import com.google.gson.annotations.SerializedName

data class ScheduleInformation(
    @SerializedName("content") val content: String,
    @SerializedName("address") val address: String,
    @SerializedName("latitude") val latitude: Float,
    @SerializedName("longitude") val longitude: Float,
    @SerializedName("color") private var _color: String,
) {
    val color: Color = _color.toColor()
}
