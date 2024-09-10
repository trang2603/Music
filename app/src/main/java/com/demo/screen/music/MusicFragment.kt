package com.demo.screen.music

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.demo.databinding.FragmentMusicBinding
import com.demo.screen.albums.adapter.AlbumsFragment
import com.demo.screen.artist.ArtistFragment
import com.demo.screen.music.adapter.MusicAdapter
import com.demo.screen.playlist.PlaylistFragment
import com.demo.screen.songs.SongsFragment
import com.google.android.material.tabs.TabLayoutMediator

class MusicFragment : Fragment() {
    private lateinit var binding: FragmentMusicBinding
    private lateinit var adapter: MusicAdapter

    val listFragment =
        listOf(
            SongsFragment(),
            ArtistFragment(),
            AlbumsFragment(),
            PlaylistFragment(),
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMusicBinding.inflate(inflater, container, false)
        setupViewPager()
        setupTabLayout()
        return binding.root
    }

    private fun setupViewPager() {
        adapter = MusicAdapter(this, listFragment)
        binding.viewPager.adapter = adapter
    }

    private fun setupTabLayout() {
        TabLayoutMediator(binding.tablayout, binding.viewPager) { tab, position ->
            tab.text =
                when (position) {
                    0 -> "Songs"
                    1 -> "Artist"
                    2 -> "Album"
                    3 -> "Playlist"
                    else -> "Songs"
                }
        }.attach()
    }
}
