package com.example.medicalapp.ui.fragments.specialist

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.medicalapp.R
import com.example.medicalapp.databinding.FragmentSpecialistCallsBinding
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
@AndroidEntryPoint
class SpecialistCallsFragment : Fragment(R.layout.fragment_specialist_calls) {

    var _binding : FragmentSpecialistCallsBinding? = null
    val binding get() = _binding!!

    var date : String? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSpecialistCallsBinding.bind(view)

        binding.btnAddCall.setOnClickListener {
            findNavController().navigate(SpecialistCallsFragmentDirections.actionSpecialistCallsFragmentToCreateCallFragment())
        }
        binding.txtDate.setOnClickListener {
            showDatePicker()
        }
        binding.btnAddCall.setOnClickListener {
            findNavController().navigate(SpecialistCallsFragmentDirections.actionSpecialistCallsFragmentToCreateCallFragment())
        }
        binding.imageView4.setOnClickListener {
            findNavController().navigateUp()
        }

    }






    private fun showDatePicker() {
        val ddd = MaterialDatePicker.Builder.datePicker()
            .setSelection(MaterialDatePicker.thisMonthInUtcMilliseconds())
            .build()
        ddd.show(parentFragmentManager,"Material")
        ddd.addOnPositiveButtonClickListener {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            binding.txtDate.text = dateFormat.format(Date(it).time)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}