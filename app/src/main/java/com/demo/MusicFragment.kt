package com.demo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.databinding.FragmentMusicBinding
import com.demo.databinding.LayoutPopupBinding

class MusicFragment : Fragment() {
    private lateinit var binding: FragmentMusicBinding
    private lateinit var adapter: MusicAdapter

    val list =
        listOf(
            Music(
                "1",
                "R.drawable.ic_launcher_foreground",
                "Bai hat 1",
                "Ca si 1",
                "Mo ta bai hat 1",
                "R.drawable.ic_heart",
                "R.drawable.ic_play",
            ),
            Music(
                "2",
                "R.drawable.ic_launcher_foreground",
                "Bai hat 2",
                "Ca si 2",
                "Mo ta bai hat 2",
                "R.drawable.ic_heart",
                "R.drawable.ic_play",
            ),
            Music(
                "3",
                "R.drawable.ic_launcher_foreground",
                "Bai hat 3",
                "Ca si 3",
                "Mo ta bai hat 3",
                "R.drawable.ic_heart",
                "R.drawable.ic_play",
            ),
            Music(
                "4",
                "R.drawable.ic_launcher_foreground",
                "Bai hat 4",
                "Ca si 4",
                "Mo ta bai hat 4",
                "R.drawable.ic_heart",
                "R.drawable.ic_play",
            ),
            Music(
                "5",
                "R.drawable.ic_launcher_foreground",
                "Bai hat 5",
                "Ca si 5",
                "Mo ta bai hat 5",
                "R.drawable.ic_heart",
                "R.drawable.ic_play",
            ),
            Music(
                "6",
                "R.drawable.ic_launcher_foreground",
                "Bai hat 6",
                "Ca si 6",
                "Mo ta bai hat 6",
                "R.drawable.ic_heart",
                "R.drawable.ic_play",
            ),
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMusicBinding.inflate(inflater, container, false)
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
            MusicAdapter(onItemLongClick = { position, viewClick ->
                val viewPopup =
                    LayoutInflater.from(requireContext()).inflate(R.layout.layout_popup, null)
                val popupWindow =
                    PopupWindow(
                        viewPopup,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        true,
                    )
                popupWindow.contentView = viewPopup
                popupWindow.showAtLocation(
                    viewClick,
                    Gravity.NO_GRAVITY,
                    viewClick.x.toInt() + viewClick.measuredWidth / 2,
                    viewClick.y.toInt() / viewClick.measuredHeight * viewClick.measuredHeight + viewClick.measuredHeight,
                )
                val dropdownBinding =
                    LayoutPopupBinding.bind(viewPopup)
                dropdownBinding.play.setOnClickListener {
                    if(list[position].isPlaying) {
                        dropdownBinding.play.setImageResource(R.drawable.ic_play)
                        list[position].isPlaying = false
                    }
                    else {
                        dropdownBinding.play.setImageResource(R.drawable.ic_pause)
                        list[position].isPlaying = true
                    }
                    adapter.notifyItemChanged(position, Bundle().apply { putBoolean("isPlaying", list[position].isPlaying) })
                }
                dropdownBinding.favourite.setOnClickListener {
                    if (list[position].isFavourite) {
                        dropdownBinding.favourite.setImageResource(R.drawable.ic_heart)
                        list[position].isFavourite = false
                    }
                    else {
                        dropdownBinding.favourite.setImageResource(R.drawable.ic_heart_full)
                        list[position].isFavourite = true
                    }
                    adapter.notifyItemChanged(position, Bundle().apply { putBoolean("isFavourite", list[position].isFavourite) })

                }
            })
        binding.recycleView.adapter = adapter
        adapter.submitList(list.toList())
    }
}
