package com.demo.music

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MusicAdapter(
    val fragment: MusicFragment,
    private val listFragment: List<Fragment>,
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = listFragment.size

    override fun createFragment(position: Int): Fragment = listFragment[position]
}
