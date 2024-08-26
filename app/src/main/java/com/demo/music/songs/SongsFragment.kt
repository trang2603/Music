package com.demo.music.songs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.R
import com.demo.databinding.FragmentSongsBinding
import com.demo.databinding.LayoutPopupBinding

class SongsFragment : Fragment() {
    private lateinit var binding: FragmentSongsBinding
    private lateinit var adapter: SongsAdapter

    val list =
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
                adapter.submitList(
                    list
                        .map { item ->
                            item.copy(
                                isPlaying =
                                    if (item.id ==
                                        music?.id
                                    ) {
                                        !item.isPlaying
                                    } else {
                                        false
                                    },
                            )
                        }.toList(),
                )
            }, onItemHeartClick = { music ->
                // TODO: update isFavourite of music -> update list
                adapter.submitList(
                    list
                        .map { item ->
                            item.copy(
                                isPlaying =
                                    if (item.id ==
                                        music?.id
                                    ) {
                                        !item.isFavourite
                                    } else {
                                        item.isFavourite
                                    },
                            )
                        }.toList(),
                )
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
                list[position].isPlaying = false
            } else {
                dropdownBinding.play.setImageResource(R.drawable.ic_pause)
                list[position].isPlaying = true
            }
            adapter.submitList(list.map { it.copy() }.toList())
        }
        dropdownBinding.favourite.setOnClickListener {
            if (list[position].isFavourite) {
                dropdownBinding.favourite.setImageResource(R.drawable.ic_heart)
                list[position].isFavourite = false
            } else {
                dropdownBinding.favourite.setImageResource(R.drawable.ic_heart_full)
                list[position].isFavourite = true
            }
            adapter.submitList(list.map { it.copy() }.toList())
        }
    }
}
