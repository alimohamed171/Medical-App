package com.example.medicalapp.ui.fragments.specialist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalapp.data.ModelResponseCreation
import com.example.medicalapp.repository.Repository
import com.example.medicalapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SpecialistViewModel @Inject constructor(
    val repository:Repository
):ViewModel() {

    private val _mutableCreateCallLiveData = MutableLiveData<Resource<String>>()
    val mutableCreateCallLiveData get() = _mutableCreateCallLiveData


    fun createCall(patientName: String,doctorId: Int,age: String,phone :String,description:String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.createCall(patientName, doctorId, age, phone, description)
                if (response.status==1){
                    _mutableCreateCallLiveData.postValue(Resource.Success(response.message))
                }else{
                    _mutableCreateCallLiveData.postValue(Resource.Error(response.message))
                }
            }catch (e:Exception){
                _mutableCreateCallLiveData.postValue(Resource.Error("An error occurred: ${e.message}"))
            }
        }

    }

}