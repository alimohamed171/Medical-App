package com.example.medicalapp.repository

import com.example.medicalapp.network.RetrofitInstance

class Repository {


    suspend fun login( email:String , password:String, deviceToken:String)
    = RetrofitInstance.api.login(email, password, deviceToken)

    suspend fun getAllUsers(type : String)
    = RetrofitInstance.api.getAllUsers(type)

}