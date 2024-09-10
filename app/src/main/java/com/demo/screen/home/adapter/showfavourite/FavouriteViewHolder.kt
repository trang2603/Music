package com.demo.screen.home.adapter.showfavourite

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.data.modelui.DataUi
import com.demo.databinding.ListFavouriteHorizontalBinding

class FavouriteViewHolder(
    val binding: ListFavouriteHorizontalBinding,
) : RecyclerView.ViewHolder(binding.root) {
    val adapter = ItemFavouriteAdapter()

    init {
        binding.listFavourite.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        binding.listFavourite.adapter = adapter
    }

    fun bindData(data: DataUi) {
        adapter.submitList(data.data as List<Any>)
    }
}
