package com.demo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.databinding.ItemMusicBinding

class MusicAdapter: ListAdapter<Music, MusicAdapter.MusicViewHolder>(MusicDiffUtil()) {
    class MusicViewHolder(val binding: ItemMusicBinding): RecyclerView.ViewHolder(binding.root){
        fun bindData(music: Music) {
            binding.apply {
                img.setImageResource(R.drawable.ic_launcher_foreground)
                name.text = music.name
                nameArtist.text = music.nameArtist
                description.text = music.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val binding = ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MusicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindData(item)
    }
}

class MusicDiffUtil: DiffUtil.ItemCallback<Music>() {
    override fun areItemsTheSame(oldItem: Music, newItem: Music): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Music, newItem: Music): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }
}