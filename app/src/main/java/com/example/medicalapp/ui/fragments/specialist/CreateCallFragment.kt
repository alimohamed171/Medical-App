package com.example.medicalapp.ui.fragments.specialist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.medicalapp.R
import com.example.medicalapp.databinding.FragmentCreateCallBinding
import com.example.medicalapp.util.Resource
import com.example.medicalapp.util.showToast

class CreateCallFragment : Fragment(R.layout.fragment_create_call) {

    var _binding :FragmentCreateCallBinding? = null
    val binding get() = _binding!!
    val viewModel:SpecialistViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCreateCallBinding.bind(view)

        binding.btnCreateUser.setOnClickListener {
            createCall()
        }


        observe()
    }

    private fun observe() {
        viewModel.mutableCreateCallLiveData.observe(viewLifecycleOwner){ response ->
            when(response){
                is Resource.Error -> showToast(response.message)
                is Resource.Success -> goBack(response.data)
            }
        }
    }

    private fun goBack(message: String?) {
        showToast(message)
        findNavController().navigateUp()
    }

    private fun createCall() {
        viewModel.createCall("teest" , age = 18 , phone = "012872" ,
            doctorId = 5, description = "jdfnsdnfsdnfsdnflksdnjukbsskdkfkdkfsdkfsd")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}