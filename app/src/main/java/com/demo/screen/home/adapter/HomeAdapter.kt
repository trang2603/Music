package com.demo.screen.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.data.modelui.DataUi
import com.demo.data.modelui.Type
import com.demo.databinding.ListFavouriteHorizontalBinding
import com.demo.databinding.ListPlaylistHorizontalBinding
import com.demo.databinding.ListRecentlyHorizontalBinding
import com.demo.databinding.PlaylistDetailVerticalBinding
import com.demo.screen.home.adapter.playlisthorizontal.PlaylistHorizontalViewHolder
import com.demo.screen.home.adapter.playlitvertical.PlaylistVerticalViewHolder
import com.demo.screen.home.adapter.recently.RecentlyViewHolder
import com.demo.screen.home.adapter.showfavourite.FavouriteViewHolder

class HomeAdapter(
    val onLongClickPlaylist: (DataUi) -> Unit,
) : ListAdapter<DataUi, RecyclerView.ViewHolder>(
        HomeItemCallback(),
    ) {
    // lay item o vi tri position
    override fun getItem(position: Int): DataUi = super.getItem(position)

    // item o vị tri position duoc bieu dien theo viewHolder nao
    override fun getItemViewType(position: Int): Int {
        val item: DataUi = getItem(position)
        val itemType = item.type.hashCode()
        return itemType
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        when (viewType) {
            Type.TYPE_PLAYLIST_HORIZONTAL.hashCode() -> {
                val binding =
                    ListPlaylistHorizontalBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false,
                    )
                return PlaylistHorizontalViewHolder(binding)
            }
            Type.TYPE_RECENTLY.hashCode() -> {
                val binding =
                    ListRecentlyHorizontalBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false,
                    )
                return RecentlyViewHolder(binding)
            }

            Type.TYPE_FAVOURITE.hashCode() -> {
                val binding =
                    ListFavouriteHorizontalBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false,
                    )
                return FavouriteViewHolder(binding)
            }

            else -> {
                val binding =
                    PlaylistDetailVerticalBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false,
                    )
                return PlaylistVerticalViewHolder(binding, onLongClickPlaylist = {
                    val item = getItem(it)
                    onLongClickPlaylist.invoke(item)
                })
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        val item = getItem(position)
        if (holder is PlaylistHorizontalViewHolder) {
            holder.bindData(item)
        } else if (holder is RecentlyViewHolder) {
            holder.bindData(item)
        } else if (holder is FavouriteViewHolder) {
            holder.bindData(item)
        } else if (holder is PlaylistVerticalViewHolder) {
            holder.bindData(item)
        }
    }
}

class HomeItemCallback : DiffUtil.ItemCallback<DataUi>() {
    override fun areItemsTheSame(
        oldItem: DataUi,
        newItem: DataUi,
    ): Boolean = oldItem.data == newItem.data

    override fun areContentsTheSame(
        oldItem: DataUi,
        newItem: DataUi,
    ): Boolean = areItemsTheSame(oldItem, newItem)
}
