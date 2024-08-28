package com.demo.home.recently

import androidx.recyclerview.widget.RecyclerView
import com.demo.R
import com.demo.data.Artist
import com.demo.databinding.ItemArtistRecentlyBinding

class ItemArtistRecentlyViewHolder(
    val binding: ItemArtistRecentlyBinding,
) : RecyclerView.ViewHolder(binding.root) {
    init {
    }

    fun bindData(artist: Any) {
        if (artist is Artist) {
            binding.imgArtist.setImageResource(R.drawable.img_artist_recently)
            binding.nameArtist.text = artist.songs.name
        }
    }
}
