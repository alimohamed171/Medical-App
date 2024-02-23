package com.example.medicalapp.ui.fragments.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalapp.data.UserData
import com.example.medicalapp.repository.Repository
import com.example.medicalapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val repository:Repository
):ViewModel() {
    private val _mutableLiveData = MutableLiveData<Resource<UserData>>()
    val mutableLiveData get() = _mutableLiveData
   // val repository = Repository()

    fun login(email : String , password : String , deviceToken : String  ){
        viewModelScope.launch (IO){
            try {
                val response = repository.login(email, password, deviceToken)
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