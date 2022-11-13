package com.venkatapps.askuniverse

import androidx.recyclerview.widget.DiffUtil

class OptionsDiffUtilsCallback(val oldList : List<String>,val newList : List<String>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // === will compare references
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldOption = oldList[oldItemPosition]
        val newOption = newList[newItemPosition]
        return oldOption == newOption
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}