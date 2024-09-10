package com.demo.screen.dialogaddplaylist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.demo.databinding.ItemNewPlaylistBinding

class ItemNewPlaylistDialogViewHolder(
    val binding: ItemNewPlaylistBinding,
    val onAddPlaylist: (String) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.add.setOnClickListener {
            onAddPlaylist.invoke(binding.edtText.text.toString())
        }
    }

    fun bindData(addPlaylist: Any) {
        binding.edtText.setText("")
    }
}
