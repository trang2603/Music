package com.demo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.base.BaseAdapter
import com.demo.databinding.ItemExampleBinding

class ExampleAdapter : BaseAdapter<String, ExampleAdapter.ExampleViewHolder>() {
    class ExampleViewHolder(
        val binding: ItemExampleBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
        }

        fun bindData(str: String) {
            binding.text.text = str
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ExampleViewHolder {
        val binding = ItemExampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExampleViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ExampleViewHolder,
        position: Int,
    ) {
        val item = getItem(position)
        holder.bindData(item)
    }
}
