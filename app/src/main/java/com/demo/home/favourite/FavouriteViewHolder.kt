package com.demo.home.favourite

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.data.Add
import com.demo.data.Songs
import com.demo.databinding.ListFavouriteHorizontalBinding

class FavouriteViewHolder(val binding: ListFavouriteHorizontalBinding): RecyclerView.ViewHolder(binding.root) {
    val adapter = ItemFavouriteAdapter()
    val listFavourite = List(5) {i->
        Songs("$i", "R.drawable.ic_song", "name song $i", "name artist $i", "description $i")
        Add("$i", Songs("$i", "R.drawable.ic_song", "name song $i", "name artist $i", "description $i"))
    }
    init {
        binding.listFavourite.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        binding.listFavourite.adapter = adapter
    }

    fun bindData(listFavourite: List<Any>) {
        adapter.submitList(listFavourite)
    }
}