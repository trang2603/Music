package com.demo.music.albums

import androidx.recyclerview.widget.RecyclerView
import com.demo.R
import com.demo.databinding.ItemAlbumBinding

class AlbumsViewHolder(
    val binding: ItemAlbumBinding,
    val onAlbumsClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnClickListener {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION)
                onAlbumsClick.invoke(position)
        }
    }

    fun bindData(albums: Albums) {
        binding.img.setImageResource(R.drawable.ic_launcher_foreground)
        binding.artistName.text = albums.artistName
        binding.date.text = albums.date
        binding.time.text = albums.time
    }
}
