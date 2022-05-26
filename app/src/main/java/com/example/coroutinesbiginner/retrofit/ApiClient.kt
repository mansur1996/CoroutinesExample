package com.example.coroutinesbiginner.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    const val BASE_URL_JSON = "https://jsonplaceholder.typicode.com/"
    const val BASE_URL_GITHUB = "https://api.github.com/"

    private fun getRetrofit(baseUrl : String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun apiService(baseUrl : String) : ApiService {
        return getRetrofit(baseUrl).create(ApiService::class.java)
    }
}