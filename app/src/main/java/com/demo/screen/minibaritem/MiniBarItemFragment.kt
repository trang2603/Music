package com.demo.screen.minibaritem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.R
import com.demo.base.BaseMVVMFragment
import com.demo.data.model.Songs
import com.demo.databinding.LayoutMiniItemBarBinding

class MiniBarItemFragment : BaseMVVMFragment<MiniBarItemViewModel>() {
    private lateinit var binding: LayoutMiniItemBarBinding
    private var song: Songs? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = LayoutMiniItemBarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            song = it.getBundle("song_data") as Songs
        }
        binding.img.setImageResource(song?.imgSong!!)
        binding.songName.text = song?.songName
        binding.artist.text = song?.artist
        binding.playPause.setImageResource(R.drawable.ic_play)
    }

    override fun observerState() {
    }

    override fun observerEffect() {
    }
}
