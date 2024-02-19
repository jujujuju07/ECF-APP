package com.example.ecf_app.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiAdapteur {
    private const val BASE_URL = "http://soignemoi.julesfogeron.com/ECF-Web/api/"
    val apiClient: Api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)
}