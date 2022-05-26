package com.example.coroutinesbiginner.retrofit

import com.example.coroutines.model.GithubUser
import com.example.coroutinesbiginner.model.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsersFromJsoPlaceHolder(): List<User>

    @GET("users")
    suspend fun getUsersFromGithub(): List<GithubUser>



}