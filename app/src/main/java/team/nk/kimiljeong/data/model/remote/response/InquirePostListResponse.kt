package team.nk.kimiljeong.data.model.remote.response

import com.google.gson.annotations.SerializedName
import team.nk.kimiljeong.data.model.remote.common.PostInformation

data class InquirePostListResponse(
    @SerializedName("post_list") val posts: List<PostInformation>,
)
