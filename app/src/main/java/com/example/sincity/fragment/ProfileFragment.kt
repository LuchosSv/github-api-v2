package com.example.sincity.fragment

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sincity.databinding.FragmentProfileBinding
import com.example.sincity.utility.KEY
import com.example.sincity.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(), View.OnClickListener {

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
        setClickListeners()

        return binding.root
    }

    private fun setClickListeners() = binding.run {
        backButton.setOnClickListener(this@ProfileFragment)
        materialButton.setOnClickListener(this@ProfileFragment)
    }

    override fun onClick(v: View?) {
        when(v){
            //onClick para volver a la lista de usuarios
            back_button -> findNavController().navigateUp()
            //onClick para agregar un usuario a favorito
            materialButton -> {
                Toast.makeText(this.context, "${profileViewModel.profileList.value!!.login} Added", Toast.LENGTH_LONG).show()
            }
        }
    }


}