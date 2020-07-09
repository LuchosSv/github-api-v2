package com.example.sincity.utility

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sincity.R
import com.example.sincity.adapter.UserAdapter
import com.example.sincity.model.UserModel
import kotlinx.android.synthetic.main.fragment_user.view.*

/**
 * Metodo que manda la lista al adapter
 */
@BindingAdapter("app:loadUsersList")
fun userWithRecyclerView(recyclerView: RecyclerView, usersList: List<UserModel>?) {
    usersList?.let {
        (recyclerView.adapter as UserAdapter).submitList(usersList)
    }
}

/**
 * Method for load image in list users
 */
@BindingAdapter("loadAvatar")
fun loadImage(image: ImageView, url: String?) {
    url?.let {
        Glide.with(image.context)
            .load(url)
            .apply(
                RequestOptions()
                    .error(R.drawable.ic_broken_image)
                    .placeholder(R.drawable.loading_animation)
            )
            .into(image)
    }
}

@BindingAdapter("loadAvatarProfile")
fun loadImageProfile(image: ImageView, url: String?) {
    url?.let {
        Glide.with(image.context)
            .load(url)
            .apply(
                RequestOptions()
                    .error(R.drawable.ic_broken_image)
                    .placeholder(R.drawable.loading_animation)
            )
            .into(image)
    }
}

/**
 * Metodo que no ayuda a mostrar el progressBar dependediendo la respuesta del viewModel
 */
@BindingAdapter("app:progressBarVisibility")
fun changeVisibility(progressBar: ProgressBar, status: String?) {
    status?.let {
        when (it) {
            LOADING -> progressBar.visibility = View.VISIBLE
            SUCCESS -> progressBar.visibility = View.GONE
            ERROR -> progressBar.visibility = View.GONE
        }
    }
}

/**
 *
 */
@BindingAdapter("app:errorMessageVisibility", "app:errorMessageText")
fun showMessageException(textView: TextView, message: String?, errorMessage: String?) {
    message?.let {
        when (it) {
            ERROR -> {
                textView.visibility = View.VISIBLE
                textView.text = errorMessage
            }
            else -> {
                textView.visibility = View.GONE
            }
        }
    }
}

@BindingAdapter("app:connectionError")
fun errorConnection(image: ImageView, message: String?) {
    message?.let {
        when (it) {
            ERROR -> {
                image.visibility = View.VISIBLE
            }
            else -> {
                image.visibility = View.GONE
            }
        }
    }
}

