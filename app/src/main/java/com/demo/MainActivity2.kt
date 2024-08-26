package com.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.databinding.ActivityMainBinding
import com.demo.music.MusicFragment

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container_music, MusicFragment())
                .commit()
        }
    }
}
