package com.example.medicalapp.ui.fragments.specialist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.medicalapp.R
import com.example.medicalapp.databinding.FragmentSpecialistHomeBinding
import com.example.medicalapp.util.SharedPrefs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpecialistHomeFragment : Fragment(R.layout.fragment_specialist_home) {


    var _binding : FragmentSpecialistHomeBinding? = null
    val binding  get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSpecialistHomeBinding.bind(view)
        navigation()
        setUpUi()
    }






    private fun setUpUi(){
        binding.textUsername.text = SharedPrefs.getUserName()
        binding.textUserDetails.text = SharedPrefs.getUserType()

    }

    private fun navigation() {
        binding.btnCalls.setOnClickListener {
            findNavController()
                .navigate(SpecialistHomeFragmentDirections.actionSpecialistHomeFragmentToSpecialistCallsFragment())
        }
        binding.imgUser.setOnClickListener{
            findNavController()
                .navigate(SpecialistHomeFragmentDirections.actionSpecialistHomeFragmentToProfileFragment(SharedPrefs.getId()))
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}