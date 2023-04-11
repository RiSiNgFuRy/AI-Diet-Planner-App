package com.example.aidietplanner_v1.Services

import com.example.aidietplanner_v1.Interfaces.RetrofitApiInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val httpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://9fff-103-105-154-120.ngrok-free.app")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun buildService(): RetrofitApiInterface {
        return retrofit.create(RetrofitApiInterface::class.java)
    }
}