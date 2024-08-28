package com.demo.music.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.demo.R
import com.demo.data.Albums
import com.demo.data.Songs
import com.demo.databinding.FragmentAlbumBinding
import com.demo.music.songs.SongsFragment

class AlbumsFragment : Fragment() {
    private lateinit var binding: FragmentAlbumBinding
    private lateinit var adapter: AlbumsAdapter

    val list =
        List(100) { i ->
            Albums(
                id = i.toString(),
                albumName = "Album $i",
                year = "Since: 2014",
                songs = Songs(),
            )
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        binding.rvAlbum.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter =
            AlbumsAdapter(onAlbumsClick = {
                val songsFragment = SongsFragment()
                requireActivity().supportFragmentManager.beginTransaction().apply {
                    add(requireActivity().findViewById<View>(R.id.container).id, songsFragment)
                    addToBackStack(SongsFragment::class.simpleName)
                    commit()
                }
            })
        binding.rvAlbum.adapter = adapter
        adapter.submitList(list.map { it.copy() })
    }
}
