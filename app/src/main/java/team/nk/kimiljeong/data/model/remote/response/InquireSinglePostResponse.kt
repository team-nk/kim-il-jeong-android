package team.nk.kimiljeong.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class InquireSinglePostResponse(
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("schedule_content") val scheduleContent: String,
    @SerializedName("address") val address: String,
    @SerializedName("color") val color: String,
    @SerializedName("comment_count") val commentCount: Long,
    @SerializedName("is_mine") val mine: Boolean,
    @SerializedName("account_id") val accountId: String,
    @SerializedName("create_time") val timeCreated: String,
)
