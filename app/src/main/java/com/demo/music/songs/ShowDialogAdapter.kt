package com.demo.music.songs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import com.demo.data.DataPlaylistUi
import com.demo.databinding.LayoutDialogBinding

class ShowDialogAdapter : ListAdapter<DataPlaylistUi, ShowDialogViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ShowDialogViewHolder {
        val binding = LayoutDialogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShowDialogViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ShowDialogViewHolder,
        position: Int,
    ) {
        val item = getItem(position)
        holder.bindData(item)
    }
}

class DiffCallback : ItemCallback<DataPlaylistUi>() {
    override fun areItemsTheSame(
        oldItem: DataPlaylistUi,
        newItem: DataPlaylistUi,
    ): Boolean = oldItem.dataPlaylistUi == newItem.dataPlaylistUi

    override fun areContentsTheSame(
        oldItem: DataPlaylistUi,
        newItem: DataPlaylistUi,
    ): Boolean = areItemsTheSame(oldItem, newItem)
}
