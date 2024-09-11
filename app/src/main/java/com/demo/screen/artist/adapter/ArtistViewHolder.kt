package com.demo.screen.artist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.demo.R
import com.demo.data.model.Artist
import com.demo.databinding.ItemArtistBinding

class ArtistViewHolder(
    val binding: ItemArtistBinding,
    val onArtistClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onArtistClick.invoke(position)
            }
        }
    }

    fun bindData(artist: Artist) {
        binding.img.setImageResource(R.drawable.ic_launcher_foreground)
        binding.artistName.text = artist.songs.artist
    }
}
