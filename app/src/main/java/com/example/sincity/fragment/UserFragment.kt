package com.example.sincity.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sincity.R
import com.example.sincity.adapter.UserAdapter
import com.example.sincity.databinding.FragmentUserBinding
import com.example.sincity.utility.RetrofitFactory
import com.example.sincity.viewmodel.UserViewModel


class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater)
        binding.lifecycleOwner = this@UserFragment
        /**
         * Brinda acceso  al viewModel para poder usarlo con dataBinding
         */
        binding.user = userViewModel

        binding.recyclerView.adapter = UserAdapter(getUserCallback())

        return binding.root
    }

    private fun getUserCallback() = UserAdapter.OnClickListener { userModel ->
        this.findNavController().navigate(
            UserFragmentDirections.actionUserFragmentToProfileFragment(userModel.login)
        )
    }

}
