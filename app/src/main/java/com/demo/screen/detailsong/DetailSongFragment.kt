package com.demo.screen.detailsong

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.demo.databinding.FragmentDetailSongBinding

class DetailSongFragment : Fragment() {
    private lateinit var binding: FragmentDetailSongBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDetailSongBinding.inflate(inflater, container, false)
        return binding.root
    }
}
