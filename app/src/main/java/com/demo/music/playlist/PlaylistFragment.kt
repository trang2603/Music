package com.demo.music.playlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.demo.databinding.FragmentPlaylistBinding

class PlaylistFragment : Fragment() {
    private lateinit var binding: FragmentPlaylistBinding
    private lateinit var adapter: PlaylistAdapter

    val list =
        List(100) { i ->
            Playlist(
                id = i.toString(),
                albumName = "Album $i",
                year = "Since: 2014",
            )
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentPlaylistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        binding.rvPlaylist.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = PlaylistAdapter()
        binding.rvPlaylist.adapter = adapter
        adapter.submitList(list.map { it.copy() })
    }
}
