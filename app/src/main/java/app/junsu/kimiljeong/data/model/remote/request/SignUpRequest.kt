package app.junsu.kimiljeong.data.model.remote.request

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("email") val email: String,
    @SerializedName("code") val verificationCode: String,
    @SerializedName("account_id") val accountId: String,
    @SerializedName("password") val password: String,
    @SerializedName("re_password") val passwordRepeat: String,
)
