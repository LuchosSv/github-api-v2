package com.example.sincity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sincity.databinding.FavoriteListAdapterBinding
import com.example.sincity.model.UserModel
import com.example.sincity.network.Entity.ProfileEntity
import kotlinx.android.synthetic.main.favorite_list_adapter.view.*

class FavoriteAdapter(private val onClickListener: FavoriteAdapter.OnClickListener) :
    ListAdapter<ProfileEntity, FavoriteAdapter.FavoriteViewHolder>(DiffCallBack) {

    class FavoriteViewHolder(private val binding: FavoriteListAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun boom(profileEntity: ProfileEntity) {
            binding.funcionaExito.text = profileEntity.login
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<ProfileEntity>() {
        override fun areItemsTheSame(oldItem: ProfileEntity, newItem: ProfileEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ProfileEntity, newItem: ProfileEntity): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteAdapter.FavoriteViewHolder(
            FavoriteListAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.imageDelete.setOnClickListener {
            onClickListener.onClick(item)
        }
        holder.boom(item)
    }

    class OnClickListener(val clickListener: (profileEntity: ProfileEntity) -> Unit) {
        fun onClick(profileEntity: ProfileEntity) = clickListener(profileEntity)
    }

}