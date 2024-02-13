package com.example.medicalapp.network

import com.example.medicalapp.data.ModelUser
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface IApiCalls {

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
       @Field("email") email:String,
       @Field("password") password:String,
       @Field("device_token") deviceToken:String
    ):ModelUser

}