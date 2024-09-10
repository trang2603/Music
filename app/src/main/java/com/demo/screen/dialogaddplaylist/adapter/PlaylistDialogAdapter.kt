package com.demo.screen.dialogaddplaylist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.data.model.AddPlaylist
import com.demo.data.model.Playlist
import com.demo.databinding.ItemNewPlaylistBinding
import com.demo.databinding.ItemPlaylistDialogBinding

class PlaylistDialogAdapter(
    val onCheckboxClick: (Playlist) -> Unit,
    val onAddPlaylist: (String) -> Unit,
) : ListAdapter<Any, RecyclerView.ViewHolder>(PlaylistDialogDiffUntill()) {
    companion object {
        const val TYPE_ADD_PLAYLIST = 1
        const val TYPE_PLAYLIST = 2
        val UPDATE_CHECK = "UPDATE_CHECK"
    }

    override fun getItemViewType(position: Int): Int {
        when (getItem(position)) {
            is AddPlaylist -> return TYPE_ADD_PLAYLIST
            is Playlist -> return TYPE_PLAYLIST
            else -> return super.getItemViewType(position)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_ADD_PLAYLIST -> {
                val binding =
                    ItemNewPlaylistBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false,
                    )
                return ItemNewPlaylistDialogViewHolder(binding, onAddPlaylist = {
                    onAddPlaylist.invoke(it)
                })
            }

            else -> {
                val binding =
                    ItemPlaylistDialogBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false,
                    )
                return ItemPlaylistDialogViewHolder(binding, onCheckboxClick = {
                    onCheckboxClick.invoke(getItem(it) as Playlist)
                })
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        val item = getItem(position)
        if (holder is ItemNewPlaylistDialogViewHolder) {
            holder.bindData(item)
        }
        if (holder is ItemPlaylistDialogViewHolder) {
            holder.bindData(item)
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>,
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            if (holder is ItemPlaylistDialogViewHolder) {
                holder.bindData(payloads, getItem(position))
            }
        }
    }
}

class PlaylistDialogDiffUntill : ItemCallback<Any>() {
    override fun areItemsTheSame(
        oldItem: Any,
        newItem: Any,
    ): Boolean {
        if (oldItem is Playlist && newItem is Playlist) {
            return oldItem.id == newItem.id
        } else if (oldItem is AddPlaylist && newItem is AddPlaylist) {
            return oldItem.id == newItem.id
        }
        return false
    }

    override fun areContentsTheSame(
        oldItem: Any,
        newItem: Any,
    ): Boolean {
        if (oldItem is Playlist && newItem is Playlist) {
            return oldItem.isCheck == newItem.isCheck
        }
        return false
    }

    override fun getChangePayload(
        oldItem: Any,
        newItem: Any,
    ): Any? {
        if (oldItem is Playlist && newItem is Playlist) {
            if (oldItem.isCheck != newItem.isCheck) {
                PlaylistDialogAdapter.UPDATE_CHECK
            }
        }
        return null
    }
}
