package app.junsu.kimiljeong.data.model.remote.common

import com.google.gson.annotations.SerializedName

data class CommentInformation(
    @SerializedName("content") val content: String,
    @SerializedName("account_id") val accountId: String,
    @SerializedName("profile") val profileUrl: String,
    @SerializedName("create_time") val timeCreated: String,
)
