package com.demo.screen.songs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.R
import com.demo.base.BaseMVVMFragment
import com.demo.databinding.FragmentSongsBinding
import com.demo.databinding.LayoutPopupBinding
import com.demo.screen.dialogaddplaylist.DialogAddPlaylistFragment
import com.demo.screen.minibar.MiniBarFragment
import com.demo.screen.songs.adapter.SongsAdapter
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class SongsFragment : BaseMVVMFragment<SongsViewModel>() {
    private lateinit var binding: FragmentSongsBinding
    private val adapter: SongsAdapter =
        SongsAdapter(onItemLongClick = { position, viewClick ->
            setupPopup(position, viewClick)
        }, onItemPlayPauseClick = { song ->
            // TODO: update playpause of music -> update list
            viewModelSongs.sendAction(SongsViewModel.Action.UpdateIconPlayPause(song))
            showMiniBar(song?.id!!)
        }, onItemHeartClick = { song ->
            // TODO: update isFavourite of music -> update list
            viewModelSongs.sendAction(SongsViewModel.Action.UpdateIconHeart(song))
        })

    private val viewModelSongs: SongsViewModel = SongsViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSongsBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycleView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recycleView.adapter = adapter
        viewModelSongs.setContentResolver(context?.contentResolver!!)
        viewModelSongs.sendAction(SongsViewModel.Action.GetList)
    }

    private fun showMiniBar(songId: String) {
        viewModelSongs.state
            .map { it.data }
            .distinctUntilChanged()
            .onEach { songList ->
                val miniBarFragment = MiniBarFragment.newInstance(songList, songId.toInt())
                childFragmentManager
                    .beginTransaction()
                    .replace(R.id.miniBar, miniBarFragment)
                    .commit()
            }.launchIn(lifecycleScope)
    }

    private fun setupPopup(
        position: Int,
        viewClick: View,
    ) {
        val viewPopup =
            LayoutInflater.from(requireContext()).inflate(R.layout.layout_popup, null)
        val popupWindow =
            PopupWindow(
                viewPopup,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true,
            )
        popupWindow.showAsDropDown(viewClick, viewClick.measuredWidth / 2, 0)
        val dropdownBinding =
            LayoutPopupBinding.bind(viewPopup)

        viewModelSongs.state
            .map { it.data }
            .distinctUntilChanged()
            .onEach { listSongs ->
                dropdownBinding.play.setImageResource(if (listSongs[position].isPlaying) R.drawable.ic_pause else R.drawable.ic_play)
                dropdownBinding.favourite.setImageResource(
                    if (listSongs[position].isFavourite) R.drawable.ic_heart_full else R.drawable.ic_heart,
                )
                dropdownBinding.play.setOnClickListener {
                    if (listSongs[position].isPlaying) {
                        dropdownBinding.play.setImageResource(R.drawable.ic_play)
                    } else {
                        dropdownBinding.play.setImageResource(R.drawable.ic_pause)
                    }
                    viewModelSongs.sendAction(SongsViewModel.Action.UpdateIconPlayPause(listSongs[position]))
                }
                dropdownBinding.favourite.setOnClickListener {
                    if (listSongs[position].isFavourite) {
                        dropdownBinding.favourite.setImageResource(R.drawable.ic_heart)
                    } else {
                        dropdownBinding.favourite.setImageResource(R.drawable.ic_heart_full)
                    }
                    viewModelSongs.sendAction(SongsViewModel.Action.UpdateIconHeart(listSongs[position]))
                }
            }.launchIn(lifecycleScope)

        dropdownBinding.add.setOnClickListener {
            val dialogAddPlaylistFragment = DialogAddPlaylistFragment()
            dialogAddPlaylistFragment.show(
                parentFragmentManager,
                DialogAddPlaylistFragment::class.simpleName,
            )
        }
    }

    override fun observerState() {
        viewModelSongs.state
            .map { it.data }
            .distinctUntilChanged()
            .onEach {
                adapter.submitList(it.toList() ?: listOf())
            }.launchIn(lifecycleScope)
    }

    override fun observerEffect() {
    }
}
