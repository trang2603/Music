package com.demo.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.R
import com.demo.databinding.FragmentHomeBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HomeAdapter
    private val viewModel: HomeViewModel = HomeViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        adapter =
            HomeAdapter(onLongClickPlaylist = {
                val dialog = BottomSheetDialog(requireContext())
                val view = layoutInflater.inflate(R.layout.bottom_sheet, null)
                dialog.setContentView(view)
                dialog.show()
            })
        binding.recycleView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recycleView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            launch {
                viewModel.initData()
            }
            launch {
                viewModel.listDataUi.collect { listData ->
                    adapter.submitList(listData.toList())
                }
            }
        }
    }
}
