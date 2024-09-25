package com.demo.screen.detailsong.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.base.BaseAdapter
import com.demo.data.model.Songs
import com.demo.data.modelui.DataDetailUi
import com.demo.data.modelui.TypeDetail
import com.demo.databinding.ItemArtistDetailsongBinding
import com.demo.databinding.ItemDetailSongBinding
import com.demo.databinding.ItemHeaderDetailsongBinding
import com.demo.databinding.ItemLyricDetailsongBinding

class DetailSongAdapter(
    val preOnClick: (DataDetailUi) -> Unit,
    val nextOnClick: (DataDetailUi) -> Unit,
    val playPauseOnClick: (DataDetailUi) -> Unit,
    val shuffleOnClick: (DataDetailUi) -> Unit,
    val addFavouriteOcClick: (DataDetailUi) -> Unit,
    val deviceOnClick: (DataDetailUi) -> Unit,
    val shareOnClick: (DataDetailUi) -> Unit,
) : ListAdapter<DataDetailUi, RecyclerView.ViewHolder>(DetailSongDiffCallback()) {
    companion object {
        val UPDATE_STATUS_PLAYPAUSE = "UPDATE_STATUS_PLAYPAUSE"
        val UPDATE_STATUS_FAVOURITE = "UPDATE_STATUS_FAVOURITE"
        val UPDATE_SONG = "UPDATE_SONG"
    }

    override fun getItemViewType(position: Int): Int {
        val item: DataDetailUi = getItem(position)
        val itemType = item.type.hashCode()
        return itemType
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        when (viewType) {
            TypeDetail.TYPE_HEADER.hashCode() -> {
                val binding =
                    ItemHeaderDetailsongBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false,
                    )
                return HeaderDetailSongViewHolder(binding)
            }

            TypeDetail.TYPE_SONG.hashCode() -> {
                val binding =
                    ItemDetailSongBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false,
                    )
                return DetailSongViewHolder(
                    binding,
                    preOnClick = { preOnClick.invoke(getItem(it)) },
                    nextOnClick = { nextOnClick.invoke(getItem(it)) },
                    playPauseOnClick = { playPauseOnClick.invoke(getItem(it)) },
                    shuffleOnClick = { shuffleOnClick.invoke(getItem(it)) },
                    addFavouriteOcClick = { addFavouriteOcClick.invoke(getItem(it)) },
                    deviceOnClick = {
                        deviceOnClick.invoke(getItem(it))
                    },
                    shareOnClick = {
                        shareOnClick.invoke(getItem(it))
                    },
                )
            }

            TypeDetail.TYPE_LYRIC.hashCode() -> {
                val binding =
                    ItemLyricDetailsongBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false,
                    )
                return LyricDetailSongViewHolder(binding)
            }

            else -> {
                val binding =
                    ItemArtistDetailsongBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false,
                    )
                return ArtistDetailSongViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        val item = getItem(position)
        if (holder is HeaderDetailSongViewHolder) {
            holder.bindData(item)
        } else if (holder is DetailSongViewHolder) {
            holder.bindData(item)
        } else if (holder is LyricDetailSongViewHolder) {
            holder.bindData(item)
        } else if (holder is ArtistDetailSongViewHolder) {
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
            val item = getItem(position)
            if (holder is DetailSongViewHolder) {
                holder.bindData(payloads, item)
            }
        }
    }
}

class DetailSongDiffCallback : ItemCallback<DataDetailUi>() {
    override fun areItemsTheSame(
        oldItem: DataDetailUi,
        newItem: DataDetailUi,
    ): Boolean {
        if (oldItem.data is Songs && newItem.data is Songs) {
            return oldItem.id == newItem.id
        } else {
            return oldItem.id == newItem.id
        }
    }

    override fun areContentsTheSame(
        oldItem: DataDetailUi,
        newItem: DataDetailUi,
    ): Boolean {
        if (oldItem.data is Songs && newItem.data is Songs) {
            return oldItem.data.isPlaying == newItem.data.isPlaying &&
                oldItem.data.isFavourite == newItem.data.isFavourite &&
                oldItem.data.id == newItem.data.id
        } else {
            return areItemsTheSame(oldItem, newItem)
        }
    }

    override fun getChangePayload(
        oldItem: DataDetailUi,
        newItem: DataDetailUi,
    ): Any? =
        if (oldItem.data is Songs && newItem.data is Songs) {
            if (oldItem.data.isPlaying != newItem.data.isPlaying) {
                DetailSongAdapter.UPDATE_STATUS_PLAYPAUSE
            } else if (oldItem.data.isFavourite != newItem.data.isFavourite) {
                DetailSongAdapter.UPDATE_STATUS_FAVOURITE
            } else {
            }
        } else {
        }
}
