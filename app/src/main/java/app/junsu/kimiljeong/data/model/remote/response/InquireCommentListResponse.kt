package app.junsu.kimiljeong.data.model.remote.response

import app.junsu.kimiljeong.data.model.remote.common.CommentInformation
import com.google.gson.annotations.SerializedName

data class InquireCommentListResponse(
    @SerializedName("comment_list") val comments: List<CommentInformation>,
)
