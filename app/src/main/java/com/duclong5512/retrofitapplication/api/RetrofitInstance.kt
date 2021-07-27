package com.duclong5512.retrofitapplication.api

import com.duclong5512.retrofitapplication.utils.ACCESS_TOKEN
import com.duclong5512.retrofitapplication.utils.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val client = OkHttpClient.Builder().apply {
        addInterceptor(Interceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer $ACCESS_TOKEN")
                .build()
            chain.proceed(request)
        })
    }.build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val API: UserApi by lazy {
        retrofit.create(UserApi::class.java)
    }

}