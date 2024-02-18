package com.example.medicalapp.ui.fragments.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.medicalapp.R
import com.example.medicalapp.data.ModelUser
import com.example.medicalapp.databinding.FragmentLoginBinding
import com.example.medicalapp.repository.Repository
import com.example.medicalapp.util.HR
import com.example.medicalapp.util.RECEPTIONIST
import com.example.medicalapp.util.REQUIRED
import com.example.medicalapp.util.Resource
import com.example.medicalapp.util.SharedPrefs
import com.example.medicalapp.util.showToast
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.fragment_login) {


    var _binding: FragmentLoginBinding? = null
    val binding get() = _binding!!
    val viewModel : LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()
            if(validateLogin(email, password)){
                viewModel.login(email , password, "ssss")
            }
        }
        observe()

    }


    private fun observe(){
        viewModel.mutableLiveData.observe(viewLifecycleOwner){ response ->
            when(response){

                is Resource.Success -> {
                    response.data?.let { userData ->
                        showToast("Welcome Back ")

                        SharedPrefs.setUserToken(userData.access_token)
                        SharedPrefs.setUserType(userData.type)
                        SharedPrefs.setUserName(userData.first_name+" "+userData.last_name)
                        SharedPrefs.setId(userData.id)
                        navigate(userData.type)
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


    private fun navigate( type: String){
        when (type) {
            HR -> findNavController().navigate(R.id.action_loginFragment_to_hrHomeFragment)
            RECEPTIONIST -> findNavController().navigate(R.id.action_loginFragment_to_specialistHomeFragment)
        }

    }

    private fun validateLogin(email: String, password: String): Boolean {
        val emailRegex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,})+$")
//val emailRegex = Regex("^\\w+([.-]?\\w+)*@gmail\\.\\w{2,}\$")
        if (email.isEmpty() ){
            binding.edtEmail.error = REQUIRED
            binding.edtEmail.requestFocus()
            return false
        }else if( password.isEmpty()){
            binding.edtPassword.error = REQUIRED
            binding.edtPassword.requestFocus()
            return false
        }else if (!emailRegex.matches(email)){
            binding.edtEmail.error = "Wrong email"
            binding.edtEmail.requestFocus()
            return false
        } else{
            return true
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}