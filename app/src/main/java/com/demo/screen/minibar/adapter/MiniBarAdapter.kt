package com.demo.screen.minibar.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.demo.data.model.Songs
import com.demo.screen.minibaritem.MiniBarItemFragment

class MiniBarAdapter(
    fragment: Fragment,
    private val songsList: List<Songs>,
    private val currentSongPosition: Int,
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = songsList.size

    override fun createFragment(position: Int): Fragment {
        val miniBarItemFragment = MiniBarItemFragment()
        miniBarItemFragment.arguments =
            Bundle().apply {
                putSerializable("song_data", songsList[position])
            }
        return miniBarItemFragment
    }
}
