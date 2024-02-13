package com.example.medicalapp.ui.fragments.hr

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.medicalapp.R
import com.example.medicalapp.adapters.CategoryAdapter
import com.example.medicalapp.databinding.FragmentEmployeeListBinding

class EmployeeListFragment : Fragment(R.layout.fragment_employee_list) {

    var _binding : FragmentEmployeeListBinding? = null
    val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEmployeeListBinding.bind(view)

        navigation()
        val dataList = listOf("All", "Doctor", "Nurse" , "HR" , "Analysis")
        val recyclerView = binding.rvCategory
        val adapter = CategoryAdapter()
        adapter.list = dataList
        recyclerView.adapter = adapter

    }

    private fun navigation() {
        binding.btnNewEmployee.setOnClickListener {
            findNavController()
                .navigate(R.id.action_employeeListFragment_to_newUserFragment)
        }
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}