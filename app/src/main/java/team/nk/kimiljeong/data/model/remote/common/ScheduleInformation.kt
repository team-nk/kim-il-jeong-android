package team.nk.kimiljeong.data.model.remote.common

import com.google.gson.annotations.SerializedName

data class ScheduleInformation(
    @SerializedName("schedule_id") val scheduleId: Int?,
    @SerializedName("content") val content: String?,
    @SerializedName("color") val color: String?,
    @SerializedName("address") val address: String?,
    @SerializedName("start_time") val startsAt: String?,
    @SerializedName("end_time") val endsAt: String?,
    @SerializedName("is_always") val isAllDay: Boolean?,
)
