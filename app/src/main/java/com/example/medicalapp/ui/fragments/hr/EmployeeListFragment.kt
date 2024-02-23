package com.example.medicalapp.ui.fragments.hr

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.medicalapp.R
import com.example.medicalapp.adapters.CategoryAdapter
import com.example.medicalapp.adapters.EmployeeListAdapter
import com.example.medicalapp.data.UsersData
import com.example.medicalapp.databinding.FragmentEmployeeListBinding
import com.example.medicalapp.util.ALL
import com.example.medicalapp.util.Resource
import com.example.medicalapp.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeListFragment : Fragment(R.layout.fragment_employee_list) {

    var _binding : FragmentEmployeeListBinding? = null
    val binding get() = _binding!!
    lateinit var categoryAdapter: CategoryAdapter
    lateinit var employeeListAdapter: EmployeeListAdapter
    val viewModel : HrViewModel by viewModels()
    lateinit var usersData: List<UsersData>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEmployeeListBinding.bind(view)

        setupCategoryAdapter()
        observe()

        navigation()



    }

    private fun observe() {
        viewModel.getUsers(ALL)
        viewModel.mutableUsersLiveData.observe(viewLifecycleOwner){ response ->
            when(response){
                is Resource.Success -> {
                    response.data?.let { data ->
                        usersData = data
                        setupAdapter()
                        binding.lottie.visibility = View.GONE
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        showToast(message)
                    }
                }
            }
        }
    }


    private fun setupAdapter() {
        val rv = binding.rvEmployee
        employeeListAdapter = EmployeeListAdapter()
        employeeListAdapter.list = usersData
        employeeListAdapter.setOnItemClickListener {
            showToast("${it.id} + ${it.first_name}")
            findNavController().navigate(EmployeeListFragmentDirections.actionEmployeeListFragmentToProfileFragment(it.id))
        }

        rv.adapter = employeeListAdapter

    }

    private fun setupCategoryAdapter() {
        val dataList = listOf("All", "Doctor", "Nurse" , "HR" , "Analysis")
        val categoryRecyclerView = binding.rvCategory
        categoryAdapter = CategoryAdapter()
        categoryAdapter.list = dataList
        categoryRecyclerView.adapter = categoryAdapter
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