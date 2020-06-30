package com.example.sincity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sincity.databinding.FragmentProfileBinding
import com.example.sincity.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val profileViewModel: ProfileViewModel by viewModels()
    private val profileArgs: ProfileFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)
        binding.lifecycleOwner = this@ProfileFragment
        binding.profile = profileViewModel

        profileViewModel.getProfileData(profileArgs.argumentProfile)

        /**
         * Boton para volver a la lista de usuarios
         */
        binding.backButton.setOnClickListener{
           findNavController().navigateUp()
       }
        return binding.root
    }


}