package com.demo.screen.detailsong.adapter

import androidx.recyclerview.widget.RecyclerView
import com.demo.data.model.LyricSong
import com.demo.databinding.ItemLyricDetailsongBinding

class LyricDetailSongViewHolder(
    val binding: ItemLyricDetailsongBinding,
) : RecyclerView.ViewHolder(binding.root) {
    init {
    }

    fun bindData(lyricSong: Any) {
        if (lyricSong is LyricSong) {
            binding.lyric.text = lyricSong.lyric
        }
    }
}
