package com.example.medicalapp.ui.fragments.specialist

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.medicalapp.R
import com.example.medicalapp.databinding.FragmentSpecialistCallsBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
@AndroidEntryPoint
class SpecialistCallsFragment : Fragment(R.layout.fragment_specialist_calls) {

    var _binding : FragmentSpecialistCallsBinding? = null
    val binding get() = _binding!!
    private val calendar = Calendar.getInstance()
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
        // val ddd = MaterialDatePicker
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            //    android.R.style.Theme_DeviceDefault_Dialog_Alert ,
            //  android.R.style.Theme_Material_Light_Dialog_Alert,
            //   AlertDialog.THEME_HOLO_LIGHT
            { _, year, monthOfYear, dayOfMonth ->
                // Update calendar with selected date
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                // Update TextView with selected date
                updateTextView()
            },
            calendar.get(Calendar.YEAR), // Initial year
            calendar.get(Calendar.MONTH), // Initial month
            calendar.get(Calendar.DAY_OF_MONTH) // Initial day
        )
        datePickerDialog.show()
    }
    private fun updateTextView() {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        date =  dateFormat.format(calendar.time)
        binding.txtDate.text = date
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}