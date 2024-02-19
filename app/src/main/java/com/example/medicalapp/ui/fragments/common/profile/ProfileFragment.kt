package com.example.medicalapp.ui.fragments.common.profile

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.medicalapp.R
import com.example.medicalapp.data.UserData
import com.example.medicalapp.databinding.FragmentProfileBinding
import com.example.medicalapp.util.Resource
import com.example.medicalapp.util.showToast


class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val args: ProfileFragmentArgs by navArgs()
    var user : UserData? = null
    var _binding : FragmentProfileBinding? = null
    val binding get() = _binding!!
    val viewModel :ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val employeeId = args.id
        viewModel.getUser(employeeId)

        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

        navigation()
        observe()

    }

    private fun setUI() {
        binding.apply {
            textAddress.text = user?.address
            textBirthday.text = user?.birthday
            textType.text = user?.type
            textEmail.text = user?.email
            val name = "${user?.first_name} ${user?.last_name}"
            textName.text = name
            textGender.text = user?.gender
            textPhone.text = user?.mobile
            textStatus.text = user?.status

        }
    }

    private fun navigation() {
              binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun observe(){
        viewModel.mutableLiveData.observe(viewLifecycleOwner){ response->
            when(response){
                is Resource.Error -> {
                    showToast(response.message)
                }
                is Resource.Success -> {
                    user = response.data
                    setUI()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}