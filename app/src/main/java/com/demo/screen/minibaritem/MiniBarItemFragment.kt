package com.demo.screen.minibaritem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.demo.R
import com.demo.base.BaseMVVMFragment
import com.demo.data.model.Songs
import com.demo.databinding.LayoutMiniItemBarBinding
import com.demo.screen.detailsong.DetailSongFragment
import com.demo.screen.songs.SongsFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MiniBarItemFragment : BaseMVVMFragment<MiniBarItemViewModel>() {
    private lateinit var binding: LayoutMiniItemBarBinding
    private var song: Songs = Songs()
    private var viewModel: MiniBarItemViewModel = MiniBarItemViewModel()

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
            song = it.getSerializable("song_data") as Songs
            viewModel.sendAction(MiniBarItemViewModel.Action.GetSong(song))
        }
        binding.img.setImageResource(song.imgSong)
        binding.songName.text = song.songName
        binding.artist.text = song.artist
        if (song.isPlaying) {
            binding.playPause.setImageResource(R.drawable.ic_pause)
        } else {
            binding.playPause.setImageResource(R.drawable.ic_play)
        }
        binding.playPause.setOnClickListener {
            viewModel.sendAction(MiniBarItemViewModel.Action.UpdateIconPlayPause(song))
        }
        binding.layoutMiniItemBar.setOnClickListener {
            showFragmentDetailSong(song)
        }
    }

    private fun showFragmentDetailSong(song: Songs) {
        val detailSongFragment = DetailSongFragment()
        val bundle = Bundle()
        detailSongFragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction().apply {
            add(requireActivity().findViewById<View>(R.id.container).id, detailSongFragment)
            addToBackStack(SongsFragment::class.simpleName)
            commit()
        }
    }

    override fun observerState() {
        viewModel.state
            .onEach {
                binding.playPause.setImageResource(if (song.isPlaying) R.drawable.ic_pause else R.drawable.ic_play)
            }.launchIn(lifecycleScope)
    }

    override fun observerEffect() {
    }

}
