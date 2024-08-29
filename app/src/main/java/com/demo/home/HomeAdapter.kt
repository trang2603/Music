package com.demo.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.data.DataUi
import com.demo.data.Type
import com.demo.databinding.ListFavouriteHorizontalBinding
import com.demo.databinding.ListPlaylistHorizontalBinding
import com.demo.databinding.ListRecentlyHorizontalBinding
import com.demo.databinding.PlaylistDetailVerticalBinding
import com.demo.home.favourite.FavouriteViewHolder
import com.demo.home.playlist.PlaylistHorizontalViewHolder
import com.demo.home.playlitvertical.PlaylistVerticalViewHolder
import com.demo.home.recently.RecentlyViewHolder

class HomeAdapter : ListAdapter<DataUi, RecyclerView.ViewHolder>(HomeItemCallback()) {
    // lay item o vi tri position
    override fun getItem(position: Int): DataUi = super.getItem(position)

    // item o vị tri position duoc bieu dien theo viewHolder nao
    override fun getItemViewType(position: Int): Int {
        val item: DataUi = getItem(position)
        val itemType = item.type.hashCode()
        return itemType
        /*var itemType = item.type
        if(itemType == Type.TYPE_PLAYLIST_HORIZONTAL) {
            return PLAYLIST_HORIZONTAL
        }*/
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
                return PlaylistVerticalViewHolder(binding)
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
