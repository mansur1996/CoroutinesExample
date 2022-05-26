package com.example.coroutinesbiginner.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinesbiginner.retrofit.ApiClient
import com.example.coroutinesbiginner.utils.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val liveData = MediatorLiveData<Resource<String>>()

    fun getUsers(): LiveData<Resource<String>> {
        val apiServiceJson = ApiClient.apiService(ApiClient.BASE_URL_JSON)
        val apiServiceGit = ApiClient.apiService(ApiClient.BASE_URL_GITHUB)

        viewModelScope.launch {
            liveData.postValue(Resource.loading(null))

            try {
                coroutineScope {
                    val async1 = async { apiServiceJson.getUsersFromJsoPlaceHolder() }
                    val async2 = async { apiServiceGit.getUsersFromGithub() }

                    val await1 = async1.await()
                    val await2 = async2.await()

                    val stringBuilder = StringBuilder()

                    for (user in await1) {
                        stringBuilder.append("Name : ").append(user.name).append("\n")
                    }

                    for (user in await2) {
                        stringBuilder.append("Login : ").append(user.login).append("\n")
                    }

                    liveData.postValue(Resource.success(stringBuilder.toString()))
                }
            } catch (e: Exception) {
                liveData.postValue(Resource.error(e.message ?: "Error", null))
            }
        }
        return liveData
    }
}


