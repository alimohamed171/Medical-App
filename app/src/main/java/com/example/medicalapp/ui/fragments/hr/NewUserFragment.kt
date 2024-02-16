package com.example.medicalapp.ui.fragments.hr

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.medicalapp.R
import com.example.medicalapp.databinding.FragmentNewUserBinding
import com.example.medicalapp.util.ANALYSIS
import com.example.medicalapp.util.Resource
import com.example.medicalapp.util.showToast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class NewUserFragment : Fragment(R.layout.fragment_new_user) {


    private val calendar = Calendar.getInstance()
    var _binding : FragmentNewUserBinding? = null
    val binding get() = _binding!!
    var date : String? = null
    val viewModel : HrViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewUserBinding.bind(view)

        binding.txtBirthday.setOnClickListener {
            showDatePicker()
        }
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnCreateUser.setOnClickListener {
            validation()
        }
        observe()
    }





private fun observe() {
    viewModel.mutableRegisterLiveData.observe(viewLifecycleOwner){ response ->
        when(response){
            is Resource.Error -> {
                response.message?.let { message ->
                    showToast(message)
                }
            }
            is Resource.Success -> {
                response.data?.let {
                    showToast(it.mobile)
                }
            }

        }

    }
}

    private fun showDatePicker() {
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
       // val dateFormat = android.text.format.DateFormat.getDateFormat(requireContext())
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        date =  dateFormat.format(calendar.time)
        Log.e("TAGGGG", "updateTextView:  $date" )
        binding.txtBirthday.text = date
    }


    private fun validation (){

        binding.apply {
            val  fName = edtFName.text.toString()
            val  lName = edtLName.text.toString()
            val  email = edtEmailAddress.text.toString()
            val  password = editPassword.text.toString()
            val  address = edtAddress.text.toString()
            val  phone = editPhone.text.toString()
            val  gender = spinnerGender.selectedItem.toString()
            var  type = spinnerSpecialist.selectedItem.toString()
            val  status = spinnerStatus.selectedItem.toString()

            if (fName ==""){
                edtFName.error = getString(R.string.required)

            }else if (lName == ""){
                edtLName.error = getString(R.string.required)
            }else if (spinnerGender.selectedItemPosition == 0){

                showToast(getString(R.string.please_select_gender))
            }else if (spinnerSpecialist.selectedItemPosition == 0){
                showToast(getString(R.string.specialist_hint))
            }else if (date == null){
                showToast(getString(R.string.birthday_hint))
            }else if (spinnerStatus.selectedItemPosition == 0){
                showToast(getString(R.string.status_hint))
            }else if (phone == ""){
                editPhone.error = getString(R.string.required)
            }else if (address == ""){
                edtAddress.error = getString(R.string.required)
            }else if (email == ""){
                edtEmailAddress.error = getString(R.string.required)
            }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.edtEmailAddress.error = getString(R.string.wrong_email_address)
            }
            else if (password == ""){
                editPassword.error = getString(R.string.required)
            }else{
                viewModel.registerUser(
                    email,password,fName,lName,gender
                    ,type, date!!,status,address,phone,type)
            }



        }
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}