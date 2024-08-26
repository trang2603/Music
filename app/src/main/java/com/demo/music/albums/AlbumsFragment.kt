package com.demo.music.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.databinding.FragmentAlbumBinding

class AlbumsFragment : Fragment() {
    private lateinit var binding: FragmentAlbumBinding
    private lateinit var adapter: AlbumsAdapter

    val list =
        List(100) { i ->
            Albums("$i", "R.drawable.ic_launcher_foreground", "artistName$i", "date$i", "time$i")
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
        adapter = AlbumsAdapter()
        binding.recycleView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recycleView.adapter = adapter
        adapter.submitList(list.map { it.copy() })
    }
}
