package com.demo.screen.dialogaddplaylist.adapter/*
package com.demo.music.dialog

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.databinding.LayoutDialogBinding

class PlaylistDialogViewHolder(val binding: LayoutDialogBinding): RecyclerView.ViewHolder(binding.root)  {
    val adapter = PlaylistDialogAdapter(onCheckboxClick = {

    }, onAddPlaylist = {

    })
    init {
        binding.recycleView.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        binding.recycleView.adapter = adapter
    }

    fun bindData(dataList: Any){
        adapter.submitList(dataList)
    }
}
*/
