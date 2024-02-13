package com.example.medicalapp.repository

import android.companion.AssociatedDevice
import com.example.medicalapp.network.RetrofitInstance

class Repository {


    suspend fun login( email:String , password:String, deviceToken:String)
    = RetrofitInstance.api.login(email, password, deviceToken)


}