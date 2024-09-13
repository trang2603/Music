package com.demo.screen.detailsong.adapter.headerdetailsong

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import com.demo.data.model.Songs
import com.demo.databinding.ItemHeaderDetailsongBinding

class HeaderDetailSongAdapter: ListAdapter<Songs, HeaderDeatilSongViewHolder>(HeaderDetailSongDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderDeatilSongViewHolder {
        val binding = ItemHeaderDetailsongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeaderDeatilSongViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeaderDeatilSongViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindData(item)
    }
}

class HeaderDetailSongDiffCallback: ItemCallback<Songs>() {
    override fun areItemsTheSame(oldItem: Songs, newItem: Songs): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Songs, newItem: Songs): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }
}
