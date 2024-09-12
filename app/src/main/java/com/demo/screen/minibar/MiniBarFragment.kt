package com.demo.screen.minibar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.base.BaseMVVMFragment
import com.demo.data.model.Songs
import com.demo.databinding.FragmentMinibarBinding
import com.demo.screen.minibar.adapter.MiniBarAdapter
import com.demo.screen.songs.SongsViewModel
import kotlin.collections.ArrayList

class MiniBarFragment : BaseMVVMFragment<MiniBarViewModel>() {
    private lateinit var binding: FragmentMinibarBinding
    private lateinit var adapter: MiniBarAdapter
    private var currentSongPosition: Int = 0
    private var songsList: List<Songs> = listOf()
    private var viewModel: SongsViewModel = SongsViewModel()

    companion object {
        fun newInstance(
            songsList: List<Songs>,
            currentSongPosition: Int,
        ): MiniBarFragment {
            val fragment = MiniBarFragment()
            val args =
                Bundle().apply {
                    putSerializable("songs_list", ArrayList(songsList))
                    putInt("current_position", currentSongPosition)
                }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMinibarBinding.inflate(inflater, container, false)
        arguments?.let {
            songsList = it.getSerializable("songs_list") as ArrayList<Songs>
            currentSongPosition = it.getInt("current_position")
        }
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MiniBarAdapter(this, songsList, currentSongPosition)
        binding.viewPager.adapter = adapter
        binding.viewPager.setCurrentItem(currentSongPosition, false)
    }

    override fun observerState() {
    }

    override fun observerEffect() {
    }
}
