package team.nk.kimiljeong.data.model.remote.request

import com.google.gson.annotations.SerializedName

data class ChangePasswordRequest(
    @SerializedName("now_password") val oldPassword: String,
    @SerializedName("new_password") val newPassword: String,
    @SerializedName("new2_password") val newPasswordRepeat: String,
)
