package com.demo.home.playlitvertical

import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import com.demo.data.DataUi
import com.demo.data.Playlist
import com.demo.databinding.PlaylistDetailVerticalBinding

class PlaylistVerticalViewHolder(
    val binding: PlaylistDetailVerticalBinding,
    val onLongClickPlaylist: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnLongClickListener {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                onLongClickPlaylist.invoke(position)
            }
            true
        }
    }

    fun bindData(data: DataUi) {
        binding.nameSong.text = (data.data as Playlist).songs.name
        binding.nameArtist.text = (data.data as Playlist).songs.nameArtist
        binding.namePlaylist.text = (data.data as Playlist).namePlaylist
    }
}
