package com.example.sincity.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sincity.R
import com.example.sincity.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val key = "MY_KEY"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater)

        //Obtenemos el preferenceManager
        val pref = PreferenceManager.getDefaultSharedPreferences(this.context)

        //Recuperamos las preferencias
        binding.buttonGet.setOnClickListener{
            val myPref = pref.getString(key, "No hay un valor para esta clave")
            showAlert(myPref!!)
        }
        //Guarga las preferencias
        binding.buttonPut.setOnClickListener{
            //Modo edicion
            val editor = pref.edit()
            editor.putString(key, "Subscriber")
            editor.apply()
            showAlert("Hemos guardado un valor")
        }
        //Elimina las Preferencias
        binding.buttonDelete.setOnClickListener{
            //Modo edicion
            val editor = pref.edit()
            editor.remove(key)
            editor.apply()
            showAlert("Hemos borrado un valor")
        }

        return binding.root
    }

    private fun showAlert(message: String){
        val builder = AlertDialog.Builder(this.context)
        builder.setTitle("MY PREFERENCE")
        builder.setMessage(message)
        val dialog = builder.create()
        dialog.show()
    }

}