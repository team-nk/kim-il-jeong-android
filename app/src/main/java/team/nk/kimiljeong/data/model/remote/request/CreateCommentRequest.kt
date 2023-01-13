package team.nk.kimiljeong.data.model.remote.request

import com.google.gson.annotations.SerializedName

data class CreateCommentRequest(
    @SerializedName("content") val content: String,
)
