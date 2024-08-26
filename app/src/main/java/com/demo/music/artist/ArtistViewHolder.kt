package com.demo.music.artist

import androidx.recyclerview.widget.RecyclerView
import com.demo.R
import com.demo.databinding.ItemArtistBinding

class ArtistViewHolder(
    val binding: ItemArtistBinding,
    val onClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onClick.invoke(position)
            }
        }
    }

    fun bindData(artist: Artist) {
        binding.img.setImageResource(R.drawable.ic_launcher_foreground)
        binding.artistName.text = artist.name
    }
}
