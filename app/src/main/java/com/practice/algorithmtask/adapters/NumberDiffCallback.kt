package com.practice.algorithmtask.adapters

import androidx.recyclerview.widget.DiffUtil
import com.practice.algorithmtask.data.NumberItem

class NumberDiffCallback(
    private val oldList: List<NumberItem>,
    private val newList: List<NumberItem>
) : DiffUtil.Callback() {
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].number == newList[newItemPosition].number
    }
}