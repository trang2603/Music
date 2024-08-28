package com.demo.home.recently

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.data.Artist
import com.demo.data.Songs
import com.demo.databinding.ListRecentlyHorizontalBinding

class RecentlyViewHolder(
    val binding: ListRecentlyHorizontalBinding,
) : RecyclerView.ViewHolder(binding.root) {
    val listRecently =
        List(5) { i ->
            Artist("$i", "R.drawable.img_artist_recently", Songs("$i", "", "Song name $i", "Artist name $i", "Album name $i"))
            Songs("$i", "", "Song name $i", "Artist name $i", "Album name $i")
        }
    val adapter = ItemRecentlyAdapter()

    init {
        binding.listRecently.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        binding.listRecently.adapter = adapter
    }

    fun bindData(listRecently: List<Any>)  {
        adapter.submitList(listRecently)
    }
}
