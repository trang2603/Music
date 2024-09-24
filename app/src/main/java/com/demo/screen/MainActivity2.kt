package com.demo.screen

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.demo.ExampleFragment
import com.demo.R
import com.demo.databinding.ActivityMainBinding
import com.demo.screen.favourite.FavouriteFragment
import com.demo.screen.home.HomeFragment
import com.demo.screen.music.MusicFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.sqrt

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var longClick: Long = 500
    private var eventDown: Long = 0
    private var firstTouchX: Float = -1f
    private var firstTouchY: Float = -1f
    private var secondTouchX: Float = -1f
    private var secondTouchY: Float = -1f

    val listFragment =
        listOf(
            HomeFragment(),
            MusicFragment(),
            FavouriteFragment(),
            ExampleFragment(),
//            SettingFragment(),
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

        when (event?.action) {
            /*MotionEvent.ACTION_DOWN -> {
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
            }*/

            MotionEvent.ACTION_DOWN -> {
                if (firstTouchX == -1f && firstTouchY == -1f) {
                    firstTouchX = event.x
                    firstTouchY = event.y
                } else {
                    secondTouchX = event.x
                    secondTouchY = event.y
                    val distance = calculator(firstTouchX, firstTouchY, secondTouchX, secondTouchY)
                    println("Khoang thoi gian cham cuar 2 lan click la: $distance")

                    firstTouchX = secondTouchX
                    firstTouchY = secondTouchY
                }
            }
        }
        return true
    }

    private fun calculator(
        firstTouchX: Float,
        firstTouchY: Float,
        secondTouchX: Float,
        secondTouchY: Float,
    ): Float {
        var dX = firstTouchX - secondTouchX
        var dY = firstTouchY - secondTouchY
        return sqrt(dX * dX - dY * dY)
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
