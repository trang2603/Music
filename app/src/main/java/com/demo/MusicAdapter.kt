package com.demo

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.databinding.ItemMusicBinding

class MusicAdapter(
    val onItemLongClick: (Int, View) -> Unit,
) : ListAdapter<Music, MusicAdapter.MusicViewHolder>(MusicDiffUtil()) {
    @SuppressLint("ClickableViewAccessibility")
    class MusicViewHolder(
        var binding: ItemMusicBinding,
        val onItemLongClick: (Int, View) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(music: Music) {
            binding.apply {
                img.setImageResource(R.drawable.ic_launcher_foreground)
                name.text = music.name
                nameArtist.text = music.nameArtist
                description.text = music.description
            }
        }

        init {
            binding.root.setOnLongClickListener {
                val position = layoutPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemLongClick.invoke(position, it)
                }

                /*val position = layoutPosition
                if (position != RecyclerView.NO_POSITION) {
                    val viewPopup =
                        LayoutInflater
                            .from(binding.root.context)
                            .inflate(R.layout.layout_popup, null)
                    var binding2 = LayoutPopupBinding.inflate(LayoutInflater.from(binding.root.context))
                    binding2.play.setOnClickListener {
                    }
                    val popupWindow =
                        PopupWindow(
                            viewPopup,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            true,
                        )
                    popupWindow.showAtLocation(
                        binding.root,
                        Gravity.NO_GRAVITY,
                        lastTouchX.toInt(),
                        lastTouchY.toInt(),
                    )
                }*/
                true
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MusicViewHolder {
        val binding = ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MusicViewHolder(binding, onItemLongClick)
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
        super.onBindViewHolder(holder, position, payloads)
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
    ): Boolean = areItemsTheSame(oldItem, newItem)
}
