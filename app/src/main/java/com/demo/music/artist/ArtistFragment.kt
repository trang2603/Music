package com.demo.music.artist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.MainActivity2
import com.demo.databinding.FragmentArtistBinding

class ArtistFragment : Fragment() {
    private lateinit var binding: FragmentArtistBinding
    private lateinit var adapter: ArtistAdapter

    val list =
        List(100) { i ->
            Artist("$i", "R.drawable.ic_launcher_background", "Artist $i")
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
            ArtistAdapter(onClick = {
                val intent = Intent(requireContext(), MainActivity2::class.java)
                startActivity(intent)
            })
        binding.recycleView.adapter = adapter
        adapter.submitList(list)
    }
}
