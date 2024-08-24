package com.demo

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.demo.databinding.ItemMusicBinding

class MusicAdapter(
    val onItemLongClick: (Int, View) -> Unit,
    // communicate with fragment
    val onItemPlayPauseClick: (Music?) -> Unit,
    val onItemHeartClick: (Music?) -> Unit,
) : ListAdapter<Music, MusicViewHolder>(MusicDiffUtil()) {
    companion object {
        val UPDATE_STATUS_AUDIO = "UPDATE_STATUS_AUDIO"
        val UPDATE_STATUS_FAVOURITE = "UPDATE_STATUS_FAVOURITE"
        val UPDATE_DATA = "UPDATE_DATA"
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MusicViewHolder {
        val binding = ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MusicViewHolder(binding, onItemLongClick, onItemPlayPauseClick = {
            // TODO: push callback -> fragment handle
            onItemPlayPauseClick.invoke(getItem(it))
        }, onItemHeartClick = {
            // TODO: push callback -> fragment handle
            onItemHeartClick.invoke(getItem(it))
        })
    }

    override fun onBindViewHolder(
        holder: MusicViewHolder,
        position: Int,
    ) {
        val item = getItem(position)
        holder.bindData(item)
    }

    override fun onBindViewHolder(
        holder: MusicViewHolder,
        position: Int,
        payloads: MutableList<Any>,
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            holder.bindData(payloads, getItem(position))
        }
    }
}

class MusicDiffUtil : DiffUtil.ItemCallback<Music>() {
    override fun areItemsTheSame(
        oldItem: Music,
        newItem: Music,
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Music,
        newItem: Music,
    ): Boolean =
        oldItem.isPlaying == newItem.isPlaying &&
            oldItem.isFavourite == newItem.isFavourite

    override fun getChangePayload(
        oldItem: Music,
        newItem: Music,
    ): Any? =
        if (oldItem.isPlaying != newItem.isPlaying) {
            MusicAdapter.UPDATE_STATUS_AUDIO
        } else if (oldItem.isFavourite != newItem.isFavourite) {
            MusicAdapter.UPDATE_STATUS_FAVOURITE
        } else {
            MusicAdapter.UPDATE_DATA
        }
}
