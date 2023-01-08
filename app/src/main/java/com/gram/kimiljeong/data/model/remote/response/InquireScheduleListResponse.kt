package com.gram.kimiljeong.data.model.remote.response

import com.gram.kimiljeong.data.model.remote.common.ScheduleInformation
import com.google.gson.annotations.SerializedName

data class InquireScheduleListResponse(
    @SerializedName("schedule_list") val schedules: List<ScheduleInformation>,
)
