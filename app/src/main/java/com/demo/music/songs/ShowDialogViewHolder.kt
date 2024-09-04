package com.demo.music.songs

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.data.AddPlaylist
import com.demo.data.DataPlaylistUi
import com.demo.data.Playlist
import com.demo.data.Songs
import com.demo.databinding.LayoutDialogBinding

class ShowDialogViewHolder(
    val binding: LayoutDialogBinding,
) : RecyclerView.ViewHolder(binding.root) {
    val adapter = PlaylistDialogAdapter()

    init {
        binding.recycleView.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        binding.recycleView.adapter = adapter
    }

    fun bindData(dataPlaylist: DataPlaylistUi) {
        adapter.submitList(dataPlaylist.dataPlaylistUi as List<Any>)
    }
}
