package com.gram.kimiljeong.data.model.remote.response

import com.gram.kimiljeong.data.model.remote.common.BirthdayBoyInformation
import com.google.gson.annotations.SerializedName

data class InquireBirthdayListResponse(
    @SerializedName("user_list") val birthdayBoys: List<BirthdayBoyInformation>,
)
