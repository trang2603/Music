package com.demo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.databinding.ItemMusicBinding

class MusicAdapter(
    val onItemLongClick: (Int, View) -> Unit,
) : ListAdapter<Music, MusicAdapter.MusicViewHolder>(MusicDiffUtil()) {
    @SuppressLint("ClickableViewAccessibility")
    class MusicViewHolder(
        var binding: ItemMusicBinding,
        val onItemLongClick: (Int, View) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(music: Music) {
            binding.apply {
                img.setImageResource(R.drawable.ic_launcher_foreground)
                name.text = music.name
                nameArtist.text = music.nameArtist
                description.text = music.description
                playPause.setImageResource(if(music.isPlaying) R.drawable.ic_pause else R.drawable.ic_play)
                heart.setImageResource(if (music.isFavourite) R.drawable.ic_heart_full else R.drawable.ic_heart)
            }
        }

        fun bindData(payloads: Bundle) {
            if (payloads.containsKey("isPlaying")) {
                val isPlaying =payloads.getBoolean("isPlaying")
                binding.playPause.setImageResource(if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play)
            }
            if(payloads.containsKey("isFavourite")) {
                val isFavourite = payloads.getBoolean("isFavourite")
                binding.heart.setImageResource(if(isFavourite) R.drawable.ic_heart_full else R.drawable.ic_heart )
            }
        }

        init {
            binding.root.setOnLongClickListener {
                val position = layoutPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemLongClick.invoke(position, it)
                }
                true
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MusicViewHolder {
        val binding = ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MusicViewHolder(binding, onItemLongClick)
    }

    override fun onBindViewHolder(
        holder: MusicViewHolder,
        position: Int,
    ) {
        val item = getItem(position)
        holder.bindData(item)
    }

    override fun onBindViewHolder(
        holder: MusicViewHolder,
        position: Int,
        payloads: MutableList<Any>,
    ) {
        if(payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        }
        else {
            val bundle = payloads[0] as Bundle
            holder.bindData(bundle)
        }


    }
}

class MusicDiffUtil : DiffUtil.ItemCallback<Music>() {
    override fun areItemsTheSame(
        oldItem: Music,
        newItem: Music,
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Music,
        newItem: Music,
    ): Boolean = oldItem.isPlaying == newItem.isPlaying

    override fun getChangePayload(oldItem: Music, newItem: Music): Any? {
        return super.getChangePayload(oldItem, newItem)
        val bundle = Bundle()
        if(oldItem.isPlaying != newItem.isPlaying) {
            bundle.putBoolean("isPlaying", newItem.isPlaying)
        }
        if(oldItem.isFavourite != newItem.isFavourite) {
            bundle.putBoolean("isFavourite", newItem.isFavourite)
        }
        return if(bundle.isEmpty) null else bundle
    }
}
