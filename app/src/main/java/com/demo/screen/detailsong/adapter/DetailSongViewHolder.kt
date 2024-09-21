package com.demo.screen.detailsong.adapter

import androidx.recyclerview.widget.RecyclerView
import com.demo.R
import com.demo.data.model.Songs
import com.demo.data.modelui.DataDetailUi
import com.demo.databinding.ItemDetailSongBinding

class DetailSongViewHolder(
    val binding: ItemDetailSongBinding,
    val preOnClick: (Int) -> Unit,
    val nextOnClick: (Int) -> Unit,
    val playPauseOnClick: (Int) -> Unit,
    val shuffleOnClick: (Int) -> Unit,
    val addFavouriteOcClick: (Int) -> Unit,
    val deviceOnClick: (Int) -> Unit,
    val shareOnClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.back.setOnClickListener {
            val position = layoutPosition
            preOnClick.invoke(position)
        }
        binding.next.setOnClickListener {
            val position = layoutPosition
            nextOnClick.invoke(position)
        }
        binding.playPause.setOnClickListener {
            val position = layoutPosition
            playPauseOnClick.invoke(position)
        }
        binding.timer.setOnClickListener {
        }
        binding.mix.setOnClickListener {
            val position = layoutPosition
            shuffleOnClick.invoke(position)
        }
        binding.addFavourite.setOnClickListener {
            val position = layoutPosition
            addFavouriteOcClick.invoke(position)
        }
        binding.device.setOnClickListener {
            val position = layoutPosition
            deviceOnClick.invoke(position)
        }
        binding.share.setOnClickListener {
            val position = layoutPosition
            shareOnClick.invoke(position)
        }
    }

    fun bindData(dataDetailUi: DataDetailUi) {
        if (dataDetailUi.data is Songs) {
            val songs = dataDetailUi.data as Songs
            binding.imgSong.setImageResource(songs.imgSong)
            binding.songName.text = songs.songName
            binding.artist.text = songs.artist
            binding.playPause.setImageResource(R.drawable.ic_pause_circle)
        }
    }

    fun bindData(
        payloads: MutableList<Any>,
        detailUi: DataDetailUi,
    ) {
        if (detailUi.data is Songs) {
            val song = detailUi.data as Songs
            for (payload in payloads) {
                when (payload) {
                    DetailSongAdapter.UPDATE_STATUS_PLAYPAUSE -> {
                        binding.playPause.setImageResource(if (song.isPlaying) R.drawable.ic_play_circle else R.drawable.ic_pause_circle)
                    }

                    DetailSongAdapter.UPDATE_STATUS_FAVOURITE -> {
                        binding.addFavourite.setImageResource(
                            if (song.isFavourite) R.drawable.ic_add_favourite else R.drawable.ic_add_favourite_circle,
                        )
                    }

                    DetailSongAdapter.UPDATE_SONG -> {
                        binding.
                    }
                }
            }
        }
    }
}
