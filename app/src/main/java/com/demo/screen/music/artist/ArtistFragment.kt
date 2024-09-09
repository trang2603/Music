package com.demo.screen.music.artist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.R
import com.demo.data.model.Artist
import com.demo.data.model.Songs
import com.demo.databinding.FragmentArtistBinding
import com.demo.music.songs.SongsFragment

class ArtistFragment : Fragment() {
    private lateinit var binding: FragmentArtistBinding
    private lateinit var adapter: ArtistAdapter

    val list =
        List(100) { i ->
            Artist("$i", "R.drawable.ic_launcher_background", Songs())
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentArtistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycleView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter =
            ArtistAdapter(onArtistClick = { artist ->
                val songsFragment = SongsFragment()
                val bundle = Bundle()
//                bundle.putString("artist", "${artist.name}")
                songsFragment.arguments = bundle
                requireActivity().supportFragmentManager.beginTransaction().apply {
                    add(requireActivity().findViewById<View>(R.id.container).id, songsFragment)
                    addToBackStack(SongsFragment::class.simpleName)
                    commit()
                }
            })
        binding.recycleView.adapter = adapter
        adapter.submitList(list)
    }
}
