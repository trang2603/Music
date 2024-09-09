package com.demo.screen.music.songs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.R
import com.demo.base.BaseMVVMFragment
import com.demo.databinding.FragmentSongsBinding
import com.demo.databinding.LayoutDialogBinding
import com.demo.databinding.LayoutPopupBinding
import com.demo.screen.music.dialog.DialogViewModel
import com.demo.screen.music.dialog.PlaylistDialogAdapter
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class SongsFragment : BaseMVVMFragment<DialogViewModel>() {
    private lateinit var binding: FragmentSongsBinding
    private lateinit var adapter: SongsAdapter
    private lateinit var adapterDialog: PlaylistDialogAdapter
    private val viewModelDialog: DialogViewModel = DialogViewModel()
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
        adapter =
            SongsAdapter(onItemLongClick = { position, viewClick ->
                setupPopup(position, viewClick)
            }, onItemPlayPauseClick = { songs ->
                // TODO: update playpause of music -> update list
                viewModelSongs.updateIconPlayPause(songs!!)
            }, onItemHeartClick = { songs ->
                // TODO: update isFavourite of music -> update list
                viewModelSongs.updateIconHeart(songs!!)
            })
        binding.recycleView.adapter = adapter
        viewModelSongs.initData()
        viewModelSongs.listSongs
            .onEach { listSongs ->
                adapter.submitList(listSongs.toList())
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

        viewModelSongs.listSongs
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
                    viewModelSongs.updateIconPlayPause(listSongs[position])
                }
                dropdownBinding.favourite.setOnClickListener {
                    if (listSongs[position].isFavourite) {
                        dropdownBinding.favourite.setImageResource(R.drawable.ic_heart)
                    } else {
                        dropdownBinding.favourite.setImageResource(R.drawable.ic_heart_full)
                    }
                    viewModelSongs.updateIconHeart(listSongs[position])
                }
            }.launchIn(lifecycleScope)

        dropdownBinding.add.setOnClickListener {
            val binding = LayoutDialogBinding.inflate(LayoutInflater.from(requireContext()))
            binding.recycleView.layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
            adapterDialog =
                PlaylistDialogAdapter(
                    onCheckboxClick = { itemClick ->
//                        viewModel.clickCheckbox(itemClick)
                        viewModelDialog.sendAction(DialogViewModel.Action.ClickCheckBox(itemClick))
                    },
                    onAddPlaylist = { namePlaylist ->
                        viewModel.clickAddPlaylist(namePlaylist)
                    },
                )
            binding.recycleView.adapter = adapterDialog
            viewModelDialog.sendAction(DialogViewModel.Action.GetList)

            viewModel.isButtonEnabled
                .onEach { isEnabled ->
                    binding.button.isEnabled = isEnabled
                }.launchIn(lifecycleScope)

            val dialogBuilder = AlertDialog.Builder(requireContext())
            dialogBuilder.setView(binding.root)
            val dialog = dialogBuilder.create()
            dialog.show()
        }
    }

    override fun observerState() {
        viewModelDialog.state
            .map { it.data }
            .distinctUntilChanged()
            .onEach {
                adapterDialog.submitList(
                    it?.toList() ?: listOf(),
                )
            }.launchIn(lifecycleScope)
    }

    override fun observerEffect() {
        TODO("Not yet implemented")
    }
}
