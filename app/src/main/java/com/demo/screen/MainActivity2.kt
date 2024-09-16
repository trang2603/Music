package com.demo.screen

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.demo.R
import com.demo.databinding.ActivityMainBinding
import com.demo.screen.favourite.FavouriteFragment
import com.demo.screen.home.HomeFragment
import com.demo.screen.music.MusicFragment
import com.demo.screen.setting.SettingFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var longClick: Long = 500
    private var eventDown: Long = 0
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

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return false
        when(event?.action) {
            MotionEvent.ACTION_DOWN -> {
                eventDown = System.currentTimeMillis()
            }
            MotionEvent.ACTION_UP -> {
                val eventUp = System.currentTimeMillis()
                val distanceTime = eventUp - eventDown
                if(distanceTime < longClick) {
                    onClick()
                }
                else {
                    onLongClick()
                }
            }
        }
        return true

    }

    private fun onLongClick() {

    }

    private fun onClick() {
        
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
        binding.viewPager.isUserInputEnabled = false
    }
}
