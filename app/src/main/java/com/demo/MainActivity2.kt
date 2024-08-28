package com.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.databinding.ActivityMainBinding
import com.demo.favourite.FavouriteFragment
import com.demo.home.HomeFragment
import com.demo.music.MusicFragment
import com.demo.setting.SettingFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    val listFragment =
        listOf(
            HomeFragment(),
            MusicFragment(),
            FavouriteFragment(),
            SettingFragment(),
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()
        setupTabLayout()
    }

    private fun setupTabLayout() {
        TabLayoutMediator(binding.tablayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.setIcon(R.drawable.ic_home)
                }
                1 -> {
                    tab.setIcon(R.drawable.ic_music)
                }
                2 -> {
                    tab.setIcon(R.drawable.ic_favourite)
                }
                3 -> {
                    tab.setIcon(R.drawable.ic_setting)
                }
            }
        }.attach()
    }

    private fun setupViewPager() {
        binding.viewPager.adapter = MainAdapter(this, listFragment)
    }
}
