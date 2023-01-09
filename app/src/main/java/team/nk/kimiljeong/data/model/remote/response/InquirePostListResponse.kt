package team.nk.kimiljeong.data.model.remote.response

import team.nk.kimiljeong.data.model.remote.common.PostInformation
import com.google.gson.annotations.SerializedName

data class InquirePostListResponse(
    @SerializedName("post_list") val posts: List<PostInformation>,
)
