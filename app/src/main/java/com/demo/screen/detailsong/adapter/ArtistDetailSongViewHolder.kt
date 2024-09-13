package com.demo.screen.detailsong.adapter

import androidx.recyclerview.widget.RecyclerView
import com.demo.data.model.Artist
import com.demo.databinding.ItemArtistDetailsongBinding

class ArtistDetailSongViewHolder(
    val binding: ItemArtistDetailsongBinding,
) : RecyclerView.ViewHolder(binding.root) {
    init {
    }

    fun bindData(artist: Any) {
        if (artist is Artist) {
            binding.imgArtist.setImageResource(artist.imgArtist.hashCode())
            binding.artist.text = artist.songs.artist
        }
    }
}
