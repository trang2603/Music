package com.demo.music.playlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.R
import com.demo.data.Playlist
import com.demo.data.Songs
import com.demo.databinding.FragmentPlaylistBinding
import com.demo.music.songs.SongsFragment

/*
class PlaylistFragment : Fragment() {
    private lateinit var binding: FragmentPlaylistBinding
    private lateinit var adapter: PlaylistAdapter

    val list =
        List(100) { i ->
            Albums(
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
        adapter =
            PlaylistAdapter(onPlaylistClick = {
                val songsFragment = SongsFragment()
                requireActivity().supportFragmentManager.beginTransaction().apply {
                    add(requireActivity().findViewById<View>(R.id.container).id, songsFragment)
                    addToBackStack(SongsFragment::class.simpleName)
                    commit()
                }
            })
        binding.rvPlaylist.adapter = adapter
        adapter.submitList(list.map { it.copy() })
    }
}
*/

class PlaylistFragment : Fragment() {
    private lateinit var binding: FragmentPlaylistBinding
    private lateinit var adapter: PlaylistAdapter

    val list =
        List(100) { i ->
            Playlist("$i", "R.drawable.ic_launcher_foreground", "name playlist $i","date$i", "time$i", Songs(), "20 songs")
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
        adapter =
            PlaylistAdapter(onPlaylistClick = {
                val songsFragment = SongsFragment()
                requireActivity().supportFragmentManager.beginTransaction().apply {
                    add(requireActivity().findViewById<View>(R.id.container).id, songsFragment)
                    addToBackStack(SongsFragment::class.simpleName)
                    commit()
                }
            })
        binding.recycleView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recycleView.adapter = adapter
        adapter.submitList(list.map { it.copy() })
    }
}
