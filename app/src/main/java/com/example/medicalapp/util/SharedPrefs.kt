package com.example.medicalapp.util

import android.content.Context
import android.content.SharedPreferences

object SharedPrefs {
    private const val PREFS_NAME = "user_data"
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun setUserToken (id : String){
        with(sharedPreferences.edit() ){
            putString("USER_TOKEN", id)
            commit()
        }
    }
    fun getUserToken(): String? {
        return sharedPreferences.getString("USER_TOKEN", "" )
    }

    fun setUserType (id : String){
        with(sharedPreferences.edit() ){
            putString("USER_TYPE", id)
            commit()
        }
    }
    fun getUserType(): String? {
        return sharedPreferences.getString("USER_TYPE", "" )
    }
    fun setUserName(id : String){
        with(sharedPreferences.edit() ){
            putString("USER_NAME", id)
            commit()
        }
    }
    fun getUserName(): String? {
        return sharedPreferences.getString("USER_NAME", "" )
    }

    fun setId(id : Int){
        with(sharedPreferences.edit() ){
            putInt("ID", id)
            commit()
        }
    }
    fun getId(): Int {
        return sharedPreferences.getInt("ID", 0 )
    }


}