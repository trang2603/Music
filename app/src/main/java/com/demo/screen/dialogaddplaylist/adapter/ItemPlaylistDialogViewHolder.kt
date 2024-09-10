package com.demo.screen.dialogaddplaylist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.demo.R
import com.demo.data.model.Playlist
import com.demo.databinding.ItemPlaylistDialogBinding

class ItemPlaylistDialogViewHolder(
    val binding: ItemPlaylistDialogBinding,
    val onCheckboxClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onCheckboxClick.invoke(position)
            }
        }
    }

    fun bindData(playlist: Any) {
        if (playlist is Playlist) {
            if (playlist.isCheck) {
                binding.imgCheck.setImageResource(R.drawable.ic_checked)
            } else {
                binding.imgCheck.setImageResource(R.drawable.ic_uncheck)
            }
            binding.namePlaylist.text = playlist.namePlaylist
            binding.totalSongs.text = playlist.totalSongs
        }
    }

    fun bindData(
        payloads: MutableList<Any>,
        playlist: Any,
    ) {
        if (playlist is Playlist) {
            for (payload in payloads) {
                when (payload) {
                    PlaylistDialogAdapter.UPDATE_CHECK -> {
                        binding.imgCheck.setImageResource(if (playlist.isCheck) R.drawable.ic_checked else R.drawable.ic_uncheck)
                    }
                }
            }
        }
    }
}
