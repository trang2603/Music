package com.demo.screen.detailsong

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.base.BaseMVVMFragment
import com.demo.databinding.FragmentDetailSongBinding
import com.demo.screen.detailsong.adapter.DetailSongAdapter
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class DetailSongFragment : BaseMVVMFragment<DetailSongViewModel>() {
    private lateinit var binding: FragmentDetailSongBinding
    private val adapter: DetailSongAdapter =
        DetailSongAdapter(
            preOnClick = { song ->
                viewModel.sendAction(DetailSongViewModel.Action.PreSong)
            },
            nextOnClick = {
                viewModel.sendAction(DetailSongViewModel.Action.NextSong)
            },
            playPauseOnClick = {
                viewModel.sendAction(DetailSongViewModel.Action.PlayPauseSong)
            },
            shuffleOnClick = {
                viewModel.sendAction(DetailSongViewModel.Action.ShuffeSong)
            },
            addFavouriteOcClick = {
                viewModel.sendAction(DetailSongViewModel.Action.AddFavouriteSong)
            },
            deviceOnClick = {
                viewModel.sendAction(DetailSongViewModel.Action.Device)
            },
            shareOnClick = {
                viewModel.sendAction(DetailSongViewModel.Action.ShareSong)
            },
        )
    private var viewModel: DetailSongViewModel = DetailSongViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDetailSongBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycleView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recycleView.adapter = adapter
        viewModel.setContentResolver(context?.contentResolver!!)
        viewModel.sendAction(DetailSongViewModel.Action.InitData)
    }

    override fun observerState() {
        viewModel.state
            .map { state ->
                state.data
//                state.data
            }.distinctUntilChanged()
            .onEach { song ->
                song?.let {
//                    adapter.submitList(listOf(DataDetailUi(TypeDetail.TYPE_SONG, it, id = song.id)))
                    adapter.submitList(it)
                }
            }.launchIn(lifecycleScope)
    }

    override fun observerEffect() {
    }
}
