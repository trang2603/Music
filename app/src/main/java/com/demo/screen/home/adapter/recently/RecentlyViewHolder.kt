package com.demo.screen.home.adapter.recently

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.data.modelui.DataUi
import com.demo.databinding.ListRecentlyHorizontalBinding

class RecentlyViewHolder(
    val binding: ListRecentlyHorizontalBinding,
) : RecyclerView.ViewHolder(binding.root) {
    val adapter = ItemRecentlyAdapter()

    init {
        binding.listRecently.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        binding.listRecently.adapter = adapter
    }

    fun bindData(data: DataUi) {
        adapter.submitList(data.data as List<Any>)
    }
}
