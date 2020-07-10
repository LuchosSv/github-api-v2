package com.example.sincity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sincity.databinding.FragmentProfileBinding
import com.example.sincity.model.ProfileModel
import com.example.sincity.network.Entity.ProfileEntity
import com.example.sincity.viewmodel.ProfileViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentProfileBinding
    private val profileArgs: ProfileFragmentArgs by navArgs()
    private val profileViewModel: ProfileViewModel by viewModels {
        ProfileViewModel.ProfileViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)
        binding.lifecycleOwner = this@ProfileFragment
        binding.profile = profileViewModel

        /**
         * Enviando los argumentos del detalle de usuario para acceder a el
         */
        profileViewModel.getProfileData(profileArgs.argumentProfile)
        setClickListeners()



        return binding.root
    }



    private fun setClickListeners() = binding.run {
        backButton.setOnClickListener(this@ProfileFragment)
        materialButton.setOnClickListener(this@ProfileFragment)
    }

    override fun onClick(v: View?) {
        when (v) {
            //onClick para volver a la lista de usuarios
            back_button -> findNavController().navigateUp()
            //onClick para agregar un usuario a favorito
            materialButton -> {
                Toast.makeText(
                    this.context,
                    "${profileViewModel.profileList.value!!.login} Added",
                    Toast.LENGTH_LONG
                ).show()
                //profileViewModel.saveLocalProfile()
                profileViewModel.profileList.observe(viewLifecycleOwner, Observer {
                    it.let {
                        profileViewModel.saveLocalProfile(ProfileEntity(login = it.login, id = it.id, avatar_url = it.avatar_url))
                    }
                })
            }
        }
    }


}