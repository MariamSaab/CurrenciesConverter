package com.kozachenko.currencieskotlin.response

import com.google.gson.annotations.SerializedName

data class CurrentResponse(
    val base: String,
    val date: String,
    //val motd: Motd,
    val rates: Rates,
    val success: Boolean
)