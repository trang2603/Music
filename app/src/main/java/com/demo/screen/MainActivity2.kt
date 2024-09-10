package com.demo.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.R
import com.demo.databinding.ActivityMainBinding
import com.demo.screen.music.MusicFragment
import com.demo.screen.setting.SettingFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    val listFragment =
        listOf(
            com.demo.screen.home
                .HomeFragment(),
            MusicFragment(),
            com.demo.screen.favourite
                .FavouriteFragment(),
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
