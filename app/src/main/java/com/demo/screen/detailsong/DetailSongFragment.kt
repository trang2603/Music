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
    private val adapter: DetailSongAdapter = DetailSongAdapter()
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
        viewModel.sendAction(DetailSongViewModel.Action.GetList)
        binding.recycleView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recycleView.adapter = adapter
    }

    override fun observerState() {
        viewModel.state
            .map { it.data }
            .distinctUntilChanged()
            .onEach {
                adapter.submitList(it?.toList())
            }.launchIn(lifecycleScope)
    }

    override fun observerEffect() {
    }
}
