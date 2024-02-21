package com.example.medicalapp.network

import com.example.medicalapp.data.ModelAllUsers
import com.example.medicalapp.data.ModelResponseCreation
import com.example.medicalapp.data.ModelUser
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IApiCalls {

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
       @Field("email") email:String,
       @Field("password") password:String,
       @Field("device_token") deviceToken:String
    ):ModelUser
    @FormUrlEncoded
    @POST("register")
    suspend fun registerUser(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("first_name") fName: String,
        @Field("last_name") lName: String,
        @Field("gender") gender: String,
        @Field("specialist") specialist: String,
        @Field("birthday") birthday: String,
        @Field("status") status: String,
        @Field("address") address: String,
        @Field("mobile") mobile: String,
        @Field("type") type: String
    ):ModelUser
    @FormUrlEncoded
    @POST("show-profile")
    suspend fun showProfile(
        @Field("user_id") id :Int
    ):ModelUser
    @GET("doctors")
    suspend fun getAllUsers(
        @Query("type")
        type :String
    ): ModelAllUsers

    // calls
    @FormUrlEncoded
    @POST("calls")
    suspend fun createCall(
        @Field("patient_name") patientName: String,
        @Field("doctor_id") doctorId: Int,
        @Field("age") age: String,
        @Field("phone") phone :String,
        @Field("description") description:String
    ):ModelResponseCreation
}