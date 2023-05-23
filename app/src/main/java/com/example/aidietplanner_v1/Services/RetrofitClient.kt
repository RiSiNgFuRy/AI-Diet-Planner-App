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
        .baseUrl("https://4acb-2401-4900-c00-a4f0-24ef-b77b-ea5d-4745.ngrok-free.app")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun buildService(): RetrofitApiInterface {
        return retrofit.create(RetrofitApiInterface::class.java)
    }
}