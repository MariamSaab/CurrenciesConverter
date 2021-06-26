package com.kozachenko.currencieskotlin.request

import com.kozachenko.currencieskotlin.response.CurrentResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiConverter {
    @GET("/convert?from=USD&to=EUR&amount=1200")
    fun getAllPosts(): CurrentResponse
}