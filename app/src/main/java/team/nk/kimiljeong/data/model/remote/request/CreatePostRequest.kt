package team.nk.kimiljeong.data.model.remote.request

import com.google.gson.annotations.SerializedName

data class CreatePostRequest(
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("schedule_id") val scheduleId: Int,
)
