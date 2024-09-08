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
import com.demo.data.Songs
import com.demo.databinding.FragmentSongsBinding
import com.demo.databinding.LayoutDialogBinding
import com.demo.databinding.LayoutPopupBinding
import com.demo.music.dialog.PlaylistDialogAdapter
import com.demo.music.viewmodel.DialogViewModel
import kotlinx.coroutines.launch

class SongsFragment : Fragment() {
    private lateinit var binding: FragmentSongsBinding
    private lateinit var adapter: SongsAdapter
    private lateinit var adapterDialog: PlaylistDialogAdapter
    private val viewModel: DialogViewModel = DialogViewModel()
    var list =
        List(100) { i ->
            Songs(
                "$i",
                "R.drawable.ic_launcher_foreground",
                "Bai hat $i",
                "Ca si $i",
                "Mo ta bai hat $i",
                "R.drawable.ic_heart",
                "R.drawable.ic_play",
            )
        }

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
            }, onItemPlayPauseClick = { music ->
                // TODO: update playpause of music -> update list
                list =
                    list.map {
                        it.copy(
                            isPlaying =
                                if (it.id == music?.id) {
                                    !it.isPlaying
                                } else {
                                    false
                                },
                        )
                    }
                adapter.submitList(list.toList())
            }, onItemHeartClick = { music ->
                // TODO: update isFavourite of music -> update list
                list =
                    list
                        .map { item ->
                            item.copy(
                                isFavourite =
                                    if (item.id ==
                                        music?.id
                                    ) {
                                        !item.isFavourite
                                    } else {
                                        item.isFavourite
                                    },
                            )
                        }
                adapter.submitList(list.toList())
            })
        binding.recycleView.adapter = adapter
        adapter.submitList(list.map { it.copy() }.toList())
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
        dropdownBinding.play.setImageResource(if (list[position].isPlaying) R.drawable.ic_pause else R.drawable.ic_play)
        dropdownBinding.favourite.setImageResource(
            if (list[position].isFavourite) R.drawable.ic_heart_full else R.drawable.ic_heart,
        )
        dropdownBinding.play.setOnClickListener {
            if (list[position].isPlaying) {
                dropdownBinding.play.setImageResource(R.drawable.ic_play)
//                list[position].isPlaying = false
            } else {
                dropdownBinding.play.setImageResource(R.drawable.ic_pause)
//                list[position].isPlaying = true
            }
            list =
                list.map {
                    it.copy(
                        isPlaying =
                            if (it.id == list[position].id) {
                                !it.isPlaying
                            } else {
                                false
                            },
                    )
                }
            adapter.submitList(list.toList())
        }
        dropdownBinding.favourite.setOnClickListener {
            if (list[position].isFavourite) {
                dropdownBinding.favourite.setImageResource(R.drawable.ic_heart)
//                list[position].isFavourite = false
            } else {
                dropdownBinding.favourite.setImageResource(R.drawable.ic_heart_full)
//                list[position].isFavourite = true
            }
            list =
                list.map {
                    it.copy(
                        isFavourite =
                            if (it.id == list[position].id) {
                                !it.isFavourite
                            } else {
                                it.isFavourite
                            },
                    )
                }
            adapter.submitList(list.toList())
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
            viewModel.dataList()

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
                    viewModel.isButtonEnabled.collect{ isEnabled ->
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
