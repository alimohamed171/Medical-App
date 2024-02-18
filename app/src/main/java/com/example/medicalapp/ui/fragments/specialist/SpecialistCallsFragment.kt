package com.example.medicalapp.ui.fragments.specialist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.medicalapp.R
import com.example.medicalapp.databinding.FragmentSpecialistCallsBinding

class SpecialistCallsFragment : Fragment(R.layout.fragment_specialist_calls) {

    var _binding : FragmentSpecialistCallsBinding? = null
    val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSpecialistCallsBinding.bind(view)

        binding.btnAddCall.setOnClickListener {
            findNavController().navigate(SpecialistCallsFragmentDirections.actionSpecialistCallsFragmentToCreateCallFragment())
        }

    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}