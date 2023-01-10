package com.gram.kimiljeong.data.model.remote.request

import com.google.gson.annotations.SerializedName

data class ChangeBirthdayRequest(
    @SerializedName("birthday") val birthday: String,
)
