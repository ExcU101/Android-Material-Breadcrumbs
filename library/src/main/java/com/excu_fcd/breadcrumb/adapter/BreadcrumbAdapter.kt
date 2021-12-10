package com.excu_fcd.breadcrumb.adapter

import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.excu_fcd.breadcrumb.R
import com.excu_fcd.breadcrumb.model.BreadcrumbItem
import com.excu_fcd.breadcrumb.view.BreadcrumbView

class BreadcrumbAdapter : RecyclerView.Adapter<BreadcrumbViewHolder>() {

    private val data = mutableListOf<BreadcrumbItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreadcrumbViewHolder {
        return BreadcrumbViewHolder(BreadcrumbView(parent.context))
    }

    override fun onBindViewHolder(holder: BreadcrumbViewHolder, position: Int) {
        val item = data[position]
        holder.breadcrumb.apply {
            setTitle(value = item.title)
            getTitle().apply {
                setOnClickListener {
                    Toast.makeText(context, "CLICKED $position", Toast.LENGTH_SHORT).show()
                }
                setTextColor(R.attr.colorAccent)
            }
            isArrowVisible = item.isArrowEnabled
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun getData(): List<BreadcrumbItem> = data

    fun add(item: BreadcrumbItem, position: Int) {
        if (position > 0) {
            val lastIndex = position - 1
            var lastItem = data[lastIndex]
            lastItem = BreadcrumbItem(lastItem.title, false, lastItem.isSelected)
            data[lastIndex] = lastItem
            notifyItemChanged(lastIndex)
        }
        data.add(position, item)
        notifyItemInserted(position)
    }

    fun add(item: BreadcrumbItem) {
        add(item, itemCount)
    }

    fun remove(item: BreadcrumbItem) {
        notifyItemRemoved(data.indexOf(item))
        data.remove(item)
    }


    fun remove(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    fun update(old: BreadcrumbItem, new: BreadcrumbItem) {
        val index = data.indexOf(old)

        data[index] = new
        notifyItemChanged(index)
    }

    fun set(list: List<BreadcrumbItem>) {
        val differ = object : DiffUtil.Callback() {

            override fun getOldListSize(): Int = data.size

            override fun getNewListSize(): Int = list.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                data[oldItemPosition].title == list[newItemPosition].title


            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                data[oldItemPosition].isArrowEnabled == list[newItemPosition].isArrowEnabled && data[oldItemPosition].isSelected == list[newItemPosition].isSelected

        }
        DiffUtil.calculateDiff(differ).also {
            data.clear()
            data.addAll(list)
            it.dispatchUpdatesTo(this)
        }
    }

}