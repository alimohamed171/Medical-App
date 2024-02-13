package com.example.medicalapp.network

import com.example.medicalapp.data.ModelAllUsers
import com.example.medicalapp.data.ModelUser
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IApiCalls {

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
       @Field("email") email:String,
       @Field("password") password:String,
       @Field("device_token") deviceToken:String
    ):ModelUser

    @GET("doctors/{type}")
    suspend fun getAllUsers(
        @Path("type")
        type :String
    ): ModelAllUsers

}