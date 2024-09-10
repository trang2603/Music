package com.demo.screen.albums

import androidx.recyclerview.widget.RecyclerView
import com.demo.data.model.Albums
import com.demo.databinding.ItemAlbumBinding

class AlbumsViewHolder(
    val binding: ItemAlbumBinding,
    val onAlbumsClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onAlbumsClick(position)
            }
        }
    }

    fun bind(albums: Albums) {
        binding.tvAlbumName.text = albums.albumName
        binding.tvYear.text = albums.year
    }
}
