package com.gram.kimiljeong.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class RenewTokenResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String,
)
