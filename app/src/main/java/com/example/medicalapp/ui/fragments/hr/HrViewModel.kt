package com.example.medicalapp.ui.fragments.hr

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalapp.data.UserData
import com.example.medicalapp.data.UsersData
import com.example.medicalapp.repository.Repository
import com.example.medicalapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
@HiltViewModel
class HrViewModel @Inject constructor(
    private val repository:Repository
):ViewModel() {

    private val _mutableUsersLiveData = MutableLiveData<Resource<List<UsersData>?>>()
    private val _mutableRegisterLiveData = MutableLiveData<Resource<UserData>>()
    val mutableRegisterLiveData get() = _mutableRegisterLiveData
    val mutableUsersLiveData get() = _mutableUsersLiveData

    fun getUsers(type:String){
        viewModelScope.launch (IO){
            try {
                val response = repository.getAllUsers(type)
                if (response.status == 1){
                    _mutableUsersLiveData.postValue(Resource.Success(response.data))
                }else{
                    _mutableUsersLiveData.postValue(Resource.Error(response.message))
                }
            }catch (e: Exception){
                _mutableUsersLiveData.postValue(Resource.Error("An error occurred: ${e.message}"))
            }
        }
    }

    fun registerUser(email: String, password: String, fName: String, lName: String,
                     gender: String, specialist: String, birthday: String, status: String,
                     address: String, mobile: String, type: String){
        try {
            viewModelScope.launch (IO) {
                val response =  repository.registerUser(email, password, fName, lName, gender,
                    specialist, birthday, status, address, mobile, type)
                if (response.status == 1){
                    _mutableRegisterLiveData.postValue(Resource.Success(response.data))
                }else{
                    _mutableRegisterLiveData.postValue(Resource.Error(response.message))
                }
            }
        }catch(e: Exception){
            _mutableRegisterLiveData.postValue(Resource.Error("An error occurred: ${e.message}"))
        }


    }

}