package com.example.sincity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sincity.adapter.UserAdapter
import com.example.sincity.databinding.FragmentUserBinding
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
        binding.user = userViewModel

        binding.recyclerView.adapter = UserAdapter(getUserCallback())

        return binding.root
    }

    private fun getUserCallback() = UserAdapter.OnClickListener { userModel ->
        Toast.makeText(context, "Selected user ${userModel.login}", Toast.LENGTH_LONG).show()
        this.findNavController().navigate(
            UserFragmentDirections.actionUserFragmentToProfileFragment(userModel.login)
        )
    }

}
