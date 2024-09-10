package com.demo.screen.playlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.R
import com.demo.base.BaseMVVMFragment
import com.demo.databinding.FragmentPlaylistBinding
import com.demo.screen.playlist.adapter.PlaylistAdapter
import com.demo.screen.songs.SongsFragment
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class PlaylistFragment : BaseMVVMFragment<PlaylistViewModel>() {
    private lateinit var binding: FragmentPlaylistBinding
    private val adapter: PlaylistAdapter =
        PlaylistAdapter(onPlaylistClick = {
            val songsFragment = SongsFragment()
            requireActivity().supportFragmentManager.beginTransaction().apply {
                add(requireActivity().findViewById<View>(R.id.container).id, songsFragment)
                addToBackStack(SongsFragment::class.simpleName)
                commit()
            }
        })

    private val viewModel: PlaylistViewModel = PlaylistViewModel()

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
        super.onViewCreated(view, savedInstanceState)
        binding.recycleView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recycleView.adapter = adapter
        viewModel.sendAction(PlaylistViewModel.Action.GetList)
    }

    override fun observerState() {
        viewModel.state
            .map { it.data }
            .distinctUntilChanged()
            .onEach {
                adapter.submitList(it?.toList() ?: listOf())
            }.launchIn(lifecycleScope)
    }

    override fun observerEffect() {
    }
}
