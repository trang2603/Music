package com.demo.music.songs

import androidx.recyclerview.widget.RecyclerView
import com.demo.R
import com.demo.data.Playlist
import com.demo.databinding.ItemPlaylistDialogBinding

class ItemPlaylistViewHolder(
    val binding: ItemPlaylistDialogBinding,
) : RecyclerView.ViewHolder(binding.root) {
    init {
    }

    fun bindData(playlist: Any) {
        if (playlist is Playlist) {
            binding.imgCheck.setImageResource(R.drawable.ic_uncheck)
            binding.namePlaylist.text = playlist.namePlaylist
            binding.totalSongs.text = playlist.totalSongs
        }
    }
}
