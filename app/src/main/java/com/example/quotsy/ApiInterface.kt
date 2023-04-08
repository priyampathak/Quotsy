package com.example.quotsy

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("quotes")
    fun getQuoteData() : Call<MyData>
}