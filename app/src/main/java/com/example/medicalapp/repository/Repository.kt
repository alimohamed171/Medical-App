package com.example.medicalapp.repository

import com.example.medicalapp.network.RetrofitInstance

class Repository {


    suspend fun login(email:String, password:String, deviceToken:String)
    = RetrofitInstance.api.login(email, password, deviceToken)

    suspend fun registerUser(email: String, password: String, fName: String, lName: String,
                             gender: String, specialist: String, birthday: String, status: String,
                             address: String, mobile: String, type: String)
    = RetrofitInstance.api.registerUser(
        email,password,fName,lName,gender,
        specialist,birthday,status,
        address,mobile,type)
    suspend fun getAllUsers(type : String)
    = RetrofitInstance.api.getAllUsers(type)

}