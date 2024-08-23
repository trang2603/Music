package com.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.databinding.FragmentMusicBinding

class MusicFragment : Fragment() {
    private lateinit var binding: FragmentMusicBinding
    private lateinit var adapter: MusicAdapter

    val list = listOf(
        Music("1", "R.drawable.ic_launcher_foreground", "Bai hat 1", "Ca si 1", "Mo ta bai hat 1", "R.drawable.ic_heart", "R.drawable.ic_play"),
        Music("2", "R.drawable.ic_launcher_foreground", "Bai hat 2", "Ca si 2", "Mo ta bai hat 2", "R.drawable.ic_heart", "R.drawable.ic_play"),
        Music("3", "R.drawable.ic_launcher_foreground", "Bai hat 3", "Ca si 3", "Mo ta bai hat 3", "R.drawable.ic_heart", "R.drawable.ic_play"),
        Music("4", "R.drawable.ic_launcher_foreground", "Bai hat 4", "Ca si 4", "Mo ta bai hat 4", "R.drawable.ic_heart", "R.drawable.ic_play"),
        Music("5", "R.drawable.ic_launcher_foreground", "Bai hat 5", "Ca si 5", "Mo ta bai hat 5", "R.drawable.ic_heart", "R.drawable.ic_play"),
        Music("6", "R.drawable.ic_launcher_foreground", "Bai hat 6", "Ca si 6", "Mo ta bai hat 6", "R.drawable.ic_heart", "R.drawable.ic_play")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMusicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycleView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = MusicAdapter()
        binding.recycleView.adapter = adapter
        adapter.submitList(list)
    }
}