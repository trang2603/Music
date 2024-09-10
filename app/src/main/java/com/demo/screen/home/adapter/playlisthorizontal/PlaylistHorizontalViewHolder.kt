package com.demo.screen.home.adapter.playlisthorizontal

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.data.model.Playlist
import com.demo.data.modelui.DataUi
import com.demo.databinding.ListPlaylistHorizontalBinding

class PlaylistHorizontalViewHolder(
    val binding: ListPlaylistHorizontalBinding,
) : RecyclerView.ViewHolder(binding.root) {
    private val adapter = PlaylistHorizontalAdapter()

    init {
        binding.listPlaylist.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        binding.listPlaylist.adapter = adapter
    }

    fun bindData(data: DataUi) {
        adapter.submitList(data.data as List<Playlist>)
    }
}
