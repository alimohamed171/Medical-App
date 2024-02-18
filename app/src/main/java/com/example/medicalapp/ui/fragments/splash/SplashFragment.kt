package com.example.medicalapp.ui.fragments.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.medicalapp.R
import com.example.medicalapp.util.HR
import com.example.medicalapp.util.RECEPTIONIST
import com.example.medicalapp.util.SharedPrefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        lifecycleScope.launch (Dispatchers.Main) {
            delay(1000)
            if(SharedPrefs.getUserType() == HR) {
               findNavController().navigate(R.id.action_splashFragment_to_hrHomeFragment)
            }else if(SharedPrefs.getUserType() == RECEPTIONIST){
                findNavController().navigate(R.id.action_splashFragment_to_specialistHomeFragment)
            }
            else {
             findNavController()
                 .navigate(R.id.action_splashFragment_to_loginFragment)
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }
}