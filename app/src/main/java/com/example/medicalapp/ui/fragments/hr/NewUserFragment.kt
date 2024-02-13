package com.example.medicalapp.ui.fragments.hr

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.medicalapp.R
import com.example.medicalapp.databinding.FragmentNewUserBinding
import java.util.Calendar


class NewUserFragment : Fragment(R.layout.fragment_new_user) {


    private val calendar = Calendar.getInstance()
    var _binding : FragmentNewUserBinding? = null
    val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewUserBinding.bind(view)


        binding.txtBirthday.setOnClickListener {
            showDatePicker()
        }

    }




    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(
            requireContext(),
        //    android.R.style.Theme_DeviceDefault_Dialog_Alert ,
            android.R.style.Theme_Material_Light_Dialog_Alert,

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
        val dateFormat = android.text.format.DateFormat.getDateFormat(requireContext())
        val date =  dateFormat.format(calendar.time)
        Log.e("TAGGGG", "updateTextView:  $date" )
        binding.txtBirthday.text = date
    }






    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}