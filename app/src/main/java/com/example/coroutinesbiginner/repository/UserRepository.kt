package com.example.coroutinesbiginner.repository

import com.example.coroutinesbiginner.retrofit.ApiService

class UserRepository(private val apiService: ApiService){
    suspend fun getUsersFromJsoPlaceHolder() = apiService.getUsersFromJsoPlaceHolder()
    suspend fun getUsersFromGithub() = apiService.getUsersFromGithub()
}