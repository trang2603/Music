package com.demo.music.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.demo.databinding.ItemAlbumBinding

class AlbumsAdapter(val onAlbumsClick: (Albums) -> Unit) : ListAdapter<Albums, AlbumsViewHolder>(AlbumsDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): AlbumsViewHolder {
        val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumsViewHolder(binding, onAlbumsClick = {
            val item = getItem(it)
            onAlbumsClick(item)
        })
    }

    override fun onBindViewHolder(
        holder: AlbumsViewHolder,
        position: Int,
    ) {
        val item = getItem(position)
        holder.bindData(item)
    }
}

class AlbumsDiffCallback : DiffUtil.ItemCallback<Albums>() {
    override fun areItemsTheSame(
        oldItem: Albums,
        newItem: Albums,
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Albums,
        newItem: Albums,
    ): Boolean = areItemsTheSame(oldItem, newItem)
}
