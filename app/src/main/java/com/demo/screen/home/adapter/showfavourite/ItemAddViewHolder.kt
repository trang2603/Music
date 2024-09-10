package com.demo.screen.home.adapter.showfavourite

import androidx.recyclerview.widget.RecyclerView
import com.demo.R
import com.demo.data.model.Add
import com.demo.databinding.ItemAddFavouriteBinding

class ItemAddViewHolder(
    val binding: ItemAddFavouriteBinding,
) : RecyclerView.ViewHolder(binding.root) {
    init {
    }

    fun bindData(add: Any) {
        if (add is Add) {
            binding.imgAdd.setImageResource(R.drawable.ic_add)
            binding.text.text = "Xem thêm"
        }
    }
}
