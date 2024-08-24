package com.demo

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.demo.databinding.ItemMusicBinding

class MusicViewHolder(
    var binding: ItemMusicBinding,
    val onItemLongClick: (Int, View) -> Unit,
    val onItemPlayPauseClick: (Int) -> Unit,
    val onItemHeartClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnLongClickListener {
            val position = layoutPosition
            if (position != RecyclerView.NO_POSITION) {
                onItemLongClick.invoke(position, it)
            }
            true
        }
        binding.playPause.setOnClickListener {
            val position = layoutPosition
            if (position != RecyclerView.NO_POSITION) {
                onItemPlayPauseClick.invoke(position)
            }
        }
        binding.heart.setOnClickListener {
            val position = layoutPosition
            if (position != RecyclerView.NO_POSITION) {
                onItemHeartClick.invoke(position)
            }
        }
    }

    fun bindData(music: Music) {
        binding.apply {
            img.setImageResource(R.drawable.ic_launcher_foreground)
            name.text = music.name
            nameArtist.text = music.nameArtist
            description.text = music.description
            playPause.setImageResource(if (music.isPlaying) R.drawable.ic_pause else R.drawable.ic_play)
            heart.setImageResource(if (music.isFavourite) R.drawable.ic_heart_full else R.drawable.ic_heart)
        }
    }

    // call when has payloads: key <-> action
    // UPDATE_STATUS_AUDIO
    // UPDATE_STATUS_FAVOURITE
    fun bindData(
        payloads: MutableList<Any>,
        music: Music,
    ) {
        for (payload in payloads) {
            when (payload) {
                MusicAdapter.UPDATE_STATUS_AUDIO -> {
                    // update playpause music.isPlaying
                    binding.playPause.setImageResource(if (music.isPlaying) R.drawable.ic_pause else R.drawable.ic_play)
                }

                MusicAdapter.UPDATE_STATUS_FAVOURITE -> {
                    // update heart music.isFavourite
                    binding.heart.setImageResource(if (music.isFavourite) R.drawable.ic_heart_full else R.drawable.ic_heart)
                }
                MusicAdapter.UPDATE_DATA -> {
                    bindData(music)
                }
            }
        }
    }
}
