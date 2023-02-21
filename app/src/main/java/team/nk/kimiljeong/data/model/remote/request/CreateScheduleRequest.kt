package team.nk.kimiljeong.data.model.remote.request

import com.google.gson.annotations.SerializedName

data class CreateScheduleRequest(
    @SerializedName("content") val content: String?,
    @SerializedName("address") val address: String?,
    @SerializedName("color") val color: String?,
    @SerializedName("start_time") val startsAt: String?,
    @SerializedName("end_time") val endsAt: String?,
    @SerializedName("is_always") val isAllDay: Boolean?,
)
