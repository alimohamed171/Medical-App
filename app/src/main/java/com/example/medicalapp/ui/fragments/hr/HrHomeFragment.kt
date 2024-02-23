package com.example.medicalapp.ui.fragments.hr

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.medicalapp.R
import com.example.medicalapp.databinding.FragmentHrHomeBinding
import com.example.medicalapp.util.SharedPrefs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HrHomeFragment : Fragment(R.layout.fragment_hr_home) {


    var _binding : FragmentHrHomeBinding? = null
    val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHrHomeBinding.bind(view)
        navigation()

        setUpUi()
    }



    private fun setUpUi(){
            binding.textUsername.text = SharedPrefs.getUserName()
            binding.textUserDetails.text = SharedPrefs.getUserType()

    }

    private fun navigation() {
        binding.btnEmployee.setOnClickListener {
            findNavController()
                .navigate(R.id.action_hrHomeFragment_to_employeeListFragment)
        }
        binding.imgUser.setOnClickListener{
            findNavController()
                .navigate(HrHomeFragmentDirections.actionHrHomeFragmentToProfileFragment(SharedPrefs.getId()))
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}