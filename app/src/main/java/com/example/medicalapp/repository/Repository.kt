package com.example.medicalapp.repository

import com.example.medicalapp.network.IApiCalls
import com.example.medicalapp.network.RetrofitInstance
import retrofit2.http.Field
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: IApiCalls
) {
    suspend fun login(email:String, password:String, deviceToken:String)
    = api.login(email, password, deviceToken)
    suspend fun registerUser(email: String, password: String, fName: String, lName: String,
                             gender: String, specialist: String, birthday: String, status: String,
                             address: String, mobile: String, type: String)
    = api.registerUser(
        email,password,fName,lName,gender,
        specialist,birthday,status,
        address,mobile,type)
    suspend fun getAllUsers(type : String)
    = api.getAllUsers(type)

    suspend fun showProfile(id: Int)= api.showProfile(id)

    suspend fun createCall(patientName: String,doctorId: Int,age: String,phone :String,description:String)
    = api.createCall(patientName, doctorId, age, phone, description)
}