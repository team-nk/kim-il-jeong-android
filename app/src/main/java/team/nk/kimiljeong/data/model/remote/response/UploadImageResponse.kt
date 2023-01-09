package team.nk.kimiljeong.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class UploadImageResponse(
    @SerializedName("image_url") val imageUrl: String,
)
