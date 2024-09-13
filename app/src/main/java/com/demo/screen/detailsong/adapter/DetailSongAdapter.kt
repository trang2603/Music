package com.demo.screen.detailsong.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.data.modelui.DataDetailUi
import com.demo.data.modelui.TypeDetail
import com.demo.databinding.ItemArtistDetailsongBinding
import com.demo.databinding.ItemDetailSongBinding
import com.demo.databinding.ItemHeaderDetailsongBinding
import com.demo.databinding.ItemLyricDetailsongBinding

class DetailSongAdapter : ListAdapter<DataDetailUi, RecyclerView.ViewHolder>(DetailSongDiffCallback()) {
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
                return DetailSongViewHolder(binding)
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
}

class DetailSongDiffCallback : ItemCallback<DataDetailUi>() {
    override fun areItemsTheSame(
        oldItem: DataDetailUi,
        newItem: DataDetailUi,
    ): Boolean = oldItem.data == newItem.data

    override fun areContentsTheSame(
        oldItem: DataDetailUi,
        newItem: DataDetailUi,
    ): Boolean = areContentsTheSame(oldItem, newItem)
}
