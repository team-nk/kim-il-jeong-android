package team.nk.kimiljeong.data.model.remote.common

import com.google.gson.annotations.SerializedName

data class PostInformation(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("schedule_content") val scheduleContent: String,
    @SerializedName("address") val address: String,
    @SerializedName("color") val color: String,
    @SerializedName("is_mine") val isMine: Boolean,
    @SerializedName("account_id") val accountId: String,
    @SerializedName("create_time") val timeCreated: String,
)
