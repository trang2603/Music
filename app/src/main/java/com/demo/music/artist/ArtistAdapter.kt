package com.demo.music.artist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.demo.databinding.ItemArtistBinding

class ArtistAdapter(
    val onClick: (Artist) -> Unit,
) : ListAdapter<Artist, ArtistViewHolder>(ArtistDiffCalback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ArtistViewHolder {
        val binding = ItemArtistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtistViewHolder(binding, onClick = {
            val item = getItem(it)
            onClick.invoke(item)
        })
    }

    override fun onBindViewHolder(
        holder: ArtistViewHolder,
        position: Int,
    ) {
        val item = getItem(position)
        holder.bindData(item)
    }
}

class ArtistDiffCalback : DiffUtil.ItemCallback<Artist>() {
    override fun areItemsTheSame(
        oldItem: Artist,
        newItem: Artist,
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Artist,
        newItem: Artist,
    ): Boolean = oldItem.name == newItem.name
}
