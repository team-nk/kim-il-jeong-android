package team.nk.kimiljeong.data.model.remote.common

import com.google.gson.annotations.SerializedName

data class ScheduleInformation(
    @SerializedName("schedule_id") val scheduleId: Int?,
    @SerializedName("content") val content: String?,
    @SerializedName("address") val address: String?,
    @SerializedName("latitude") val latitude: Float?,
    @SerializedName("longitude") val longitude: Float?,
    @SerializedName("color") val color: String?,
    @SerializedName("start_time") val startsAt: String?,
    @SerializedName("end_time") val endsAt: String?,
    @SerializedName("is_always") val isAllDay: Boolean?,
)
