package app.junsu.kimiljeong.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class BooleanResponse(
    @SerializedName("true") val success: Boolean,
)
