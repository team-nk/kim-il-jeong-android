package team.nk.kimiljeong.data.model.remote.response

import com.google.gson.annotations.SerializedName
import team.nk.kimiljeong.data.model.remote.common.ScheduleInformation

data class InquireScheduleListResponse(
    @SerializedName("schedule_list") val schedules: List<ScheduleInformation>,
)
