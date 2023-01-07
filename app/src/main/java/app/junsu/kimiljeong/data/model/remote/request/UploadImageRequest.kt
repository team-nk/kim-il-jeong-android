package app.junsu.kimiljeong.data.model.remote.request

import com.google.gson.annotations.SerializedName

data class UploadImageRequest(
    @SerializedName("image") val image: String,
)
