package app.junsu.kimiljeong.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class SelfInformationResponse(
    @SerializedName("profile") val profileUrl: String,
    @SerializedName("account_id") val id: String,
    @SerializedName("email") val email: String,
)
