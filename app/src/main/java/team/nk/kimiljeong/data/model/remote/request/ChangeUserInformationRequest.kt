package team.nk.kimiljeong.data.model.remote.request

import com.google.gson.annotations.SerializedName

data class ChangeUserInformationRequest(
    @SerializedName("email") val email: String,
    @SerializedName("account_id") val accountId: String,
    @SerializedName("profile") val profile: String,
)