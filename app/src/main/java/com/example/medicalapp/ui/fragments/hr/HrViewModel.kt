package com.example.medicalapp.ui.fragments.hr

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalapp.data.UserData
import com.example.medicalapp.data.UsersData
import com.example.medicalapp.repository.Repository
import com.example.medicalapp.util.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.lang.Exception

class HrViewModel:ViewModel() {

    private val _mutableLiveData = MutableLiveData<Resource<List<UsersData>?>>()
    val mutableLiveData get() = _mutableLiveData
    private val repository = Repository()


    fun getUsers(type:String){
        viewModelScope.launch (IO){
            try {
                val response = repository.getAllUsers(type)
                if (response.status == 1){
                    _mutableLiveData.postValue(Resource.Success(response.data))
                }else{
                    _mutableLiveData.postValue(Resource.Error(response.message))
                }
            }catch (e: Exception){
                _mutableLiveData.postValue(Resource.Error("An error occurred: ${e.message}"))
            }
        }
    }



}