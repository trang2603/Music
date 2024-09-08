package com.demo.music.songs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.R
import com.demo.databinding.FragmentSongsBinding
import com.demo.databinding.LayoutDialogBinding
import com.demo.databinding.LayoutPopupBinding
import com.demo.music.dialog.PlaylistDialogAdapter
import com.demo.music.dialog.DialogViewModel
import kotlinx.coroutines.launch

class SongsFragment : Fragment() {
    private lateinit var binding: FragmentSongsBinding
    private lateinit var adapter: SongsAdapter
    private lateinit var adapterDialog: PlaylistDialogAdapter
    private val viewModel: DialogViewModel = DialogViewModel()
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
        viewLifecycleOwner.lifecycleScope.launch {
            launch {
                viewModelSongs.initData()
            }
            launch {
                viewModelSongs.listSongs.collect { listSongs ->
                    adapter.submitList(listSongs.toList())
                }
            }
        }
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

        viewLifecycleOwner.lifecycleScope.launch {
            viewModelSongs.listSongs.collect { listSongs ->
                dropdownBinding.play.setImageResource(if (listSongs[position].isPlaying) R.drawable.ic_pause else R.drawable.ic_play)
                dropdownBinding.favourite.setImageResource(if (listSongs[position].isFavourite) R.drawable.ic_heart_full else R.drawable.ic_heart)

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
            }
        }

        dropdownBinding.add.setOnClickListener {
            val binding = LayoutDialogBinding.inflate(LayoutInflater.from(requireContext()))
            binding.recycleView.layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
            adapterDialog =
                PlaylistDialogAdapter(
                    onCheckboxClick = { itemClick ->
                        viewModel.clickCheckbox(itemClick)
                    },
                    onAddPlaylist = { namePlaylist ->
                        viewModel.clickAddPlaylist(namePlaylist)
                    },
                )
            binding.recycleView.adapter = adapterDialog
            viewLifecycleOwner.lifecycleScope.launch {
                launch {
                    viewModel.initData()
                }
                launch {
                    viewModel.dataList.collect { dataList ->
                        adapterDialog.submitList(dataList.toList())
                    }
                }
                launch {
                    viewModel.isButtonEnabled.collect { isEnabled ->
                        binding.button.isEnabled = isEnabled
                    }
                }
            }

            val dialogBuilder = AlertDialog.Builder(requireContext())
            dialogBuilder.setView(binding.root)
            val dialog = dialogBuilder.create()
            dialog.show()
        }
    }
}
