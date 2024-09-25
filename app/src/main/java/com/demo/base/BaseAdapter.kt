package com.demo.base

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

open class Model(val id: Int)
abstract class BaseAdapter<T , VH : RecyclerView.ViewHolder>(private val diffCallback: DiffUtil.ItemCallback<T>) : RecyclerView.Adapter<VH>() {
    private var items: MutableList<T> = mutableListOf()

    abstract fun oncreateViewHolder(parent: ViewGroup, viewType: Int): VH
    abstract fun onbindViewHolder(holder: VH, position: Int/*, viewType: Int*/)
    open fun getItemViewType(position: Int, item: T): Int {
        return super.getItemViewType(position)
    }
    open fun onbindViewHolder(
        holder: VH,
        position: Int,
        viewType: Int,
        payloads: MutableList<Any>
    ) {

    }

    override fun getItemViewType(position: Int): Int {
        return getItemViewType(position, items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return oncreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        val viewType = getItemViewType(position, item)
        onbindViewHolder(holder, position,/* viewType*/)
    }

    override fun onBindViewHolder(holder: VH, position: Int, payloads: MutableList<Any>) {
        val item = items[position]
        val viewType = getItemViewType(position, item)
        onbindViewHolder(holder, position, viewType, payloads)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    open fun updateItems(newItems: List<T>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
    open fun getItem(position: Int): T {
        return items[position]
    }
    open fun getItems(): List<T>{
        return items
    }

}
