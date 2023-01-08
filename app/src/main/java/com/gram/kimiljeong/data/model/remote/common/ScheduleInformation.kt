package com.gram.kimiljeong.data.model.remote.common

import com.gram.kimiljeong.data.common.Color
import com.gram.kimiljeong.data.util.toColor
import com.google.gson.annotations.SerializedName

data class ScheduleInformation(
    @SerializedName("content") val content: String?,
    @SerializedName("address") val address: String?,
    @SerializedName("latitude") val latitude: Float?,
    @SerializedName("longitude") val longitude: Float?,
    @SerializedName("color") private var _color: String?,
    @SerializedName("start_time") val startsAt: String?,
    @SerializedName("end_time") val endsAt: String?,
    @SerializedName("always") val isAllDay: Boolean?,
) {
    val color: Color? = _color?.toColor()
}
