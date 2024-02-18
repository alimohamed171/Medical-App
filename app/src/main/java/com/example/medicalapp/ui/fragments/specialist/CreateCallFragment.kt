package com.example.medicalapp.ui.fragments.specialist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.medicalapp.R
import com.example.medicalapp.databinding.FragmentCreateCallBinding

class CreateCallFragment : Fragment(R.layout.fragment_create_call) {

   var _binding :FragmentCreateCallBinding? = null
    val binding get() = _binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCreateCallBinding.bind(view)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}