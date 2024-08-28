package com.demo.home.playlist

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.data.Playlist
import com.demo.databinding.PlaylistHorizontalBinding

class PlaylistHorizontalViewHolder(
    val binding: PlaylistHorizontalBinding,
) : RecyclerView.ViewHolder(binding.root) {
    private val adapter = PlaylistHorizontalAdapter()

    init {
        binding.listPlaylist.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        binding.listPlaylist.adapter = adapter
    }

    fun bindData(listPlaylist: List<Playlist>) {
        adapter.submitList(listPlaylist)
    }
}
