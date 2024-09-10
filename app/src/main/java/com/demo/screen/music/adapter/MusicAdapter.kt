package com.demo.screen.music.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.demo.screen.music.MusicFragment

class MusicAdapter(
    val fragment: MusicFragment,
    private val listFragment: List<Fragment>,
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = listFragment.size

    override fun createFragment(position: Int): Fragment = listFragment[position]
}
