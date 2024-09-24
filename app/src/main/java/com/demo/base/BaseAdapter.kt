package com.demo.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class BaseAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    fun updateData(newItems: List<T>) {
        items.clear()
        items.addAll(newItems)
    }

    private val items: MutableList<T> = mutableListOf()

    override fun getItemCount(): Int = items.size

    fun getItem(position: Int): T = items[position]

    abstract override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): VH

    abstract override fun onBindViewHolder(
        holder: VH,
        position: Int,
    )
}
