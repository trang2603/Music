package com.demo.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.data.Add
import com.demo.data.Artist
import com.demo.data.Playlist
import com.demo.data.Songs
import com.demo.databinding.ListFavouriteHorizontalBinding
import com.demo.databinding.ListPlaylistHorizontalBinding
import com.demo.databinding.ListRecentlyHorizontalBinding
import com.demo.databinding.PlaylistDetailVerticalBinding
import com.demo.home.favourite.FavouriteViewHolder
import com.demo.home.playlist.PlaylistHorizontalAdapter
import com.demo.home.playlist.PlaylistHorizontalViewHolder
import com.demo.home.playlitvertical.PlaylistVerticalViewHolder
import com.demo.home.recently.RecentlyViewHolder

class HomeAdapter : ListAdapter<Any, RecyclerView.ViewHolder>(HomeItemCallback()) {
    companion object {
        const val PLAYLIST_HORIZONTAL = 1
        const val RECENTLY = 2
        const val FAVOURITE = 3
        const val PLAYLIST_VERTICAL = 4
    }

    override fun getItemViewType(position: Int): Int {
        when (val item = getItem(position)) {
            is List<*> -> {
                if (item.isNotEmpty()) {
                    when (item.first()) {
                        is Playlist -> return PLAYLIST_HORIZONTAL
                        is Songs, is Artist -> return RECENTLY
                        is Songs, is Add -> return FAVOURITE
                        else -> throw IllegalArgumentException("")
                    }
                } else throw IllegalArgumentException("")
            }

            else ->
                return PLAYLIST_VERTICAL
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {
            PLAYLIST_HORIZONTAL -> {
                val binding = ListPlaylistHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return PlaylistHorizontalViewHolder(binding)
            }
            RECENTLY -> {
                val binding = ListRecentlyHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return RecentlyViewHolder(binding)
            }
            FAVOURITE -> {
                val binding = ListFavouriteHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return FavouriteViewHolder(binding)
            }
            else -> {
                val binding = PlaylistDetailVerticalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return PlaylistVerticalViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is PlaylistHorizontalViewHolder -> {
                val listPlaylist = getItem(position) as List<Playlist>
                holder.bindData(listPlaylist)
            }
            is RecentlyViewHolder -> {
                val listRecently = getItem(position) as List<Any>
                holder.bindData(listRecently)
            }
            is FavouriteViewHolder -> {
                val listFavourite = getItem(position) as List<Any>
                holder.bindData(listFavourite)
            }
            is PlaylistVerticalViewHolder -> {
                val item = getItem(position) as Playlist
                holder.bindData(item)
            }
        }
    }
}




class HomeItemCallback : DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        TODO("Not yet implemented")
    }
}