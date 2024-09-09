package com.demo.screen

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainAdapter(
    val fragment: MainActivity2,
    private val listFragment: List<Fragment>,
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = listFragment.size

    override fun createFragment(position: Int): Fragment = listFragment[position]
}
