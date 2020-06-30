package com.example.sincity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sincity.databinding.UserListAdapterBinding
import com.example.sincity.model.UserModel

class UserAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<UserModel, UserAdapter.UserViewHolder>(DiffCallBack) {

    class UserViewHolder(private val binding: UserListAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun boom(userModel: UserModel) {
            binding.user = userModel
            binding.executePendingBindings()
        }
    }

    /**
     * DiffCallBack
     */
    companion object DiffCallBack : DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            UserListAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        holder.boom(item)
    }

    class OnClickListener(val clickListener: (userModel: UserModel) -> Unit) {
        fun onClick(userModel: UserModel) = clickListener(userModel)
    }

}