package com.gram.kimiljeong.data.model.remote.common

import com.google.gson.annotations.SerializedName

data class BirthdayBoyInformation(
    @SerializedName("account_id") val id: String,
    @SerializedName("age") val age: Int,
    @SerializedName("birthday") val birthday: String,
)
