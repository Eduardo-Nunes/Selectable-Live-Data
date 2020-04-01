package com.eduardonunes.selectablelistlivedata.infrastructure

import androidx.recyclerview.widget.RecyclerView

data class ListHolder<T>(var list: MutableList<T> = mutableListOf()) {
    var indexChanged: Int = -1
    private var updateType: UpdateType? = null

    fun updateList(newList: MutableList<T>) {
        list = newList
        updateType = UpdateType.UPDATE_LIST
    }

    fun addItem(position: Int, item: T) {
        list.add(position, item)
        indexChanged = position
        updateType = UpdateType.INSERT
    }

    fun removeItemAt(position: Int) {
        val item = list[position]
        list.remove(item)
        indexChanged = position
        updateType = UpdateType.REMOVE
    }

    fun setItem(position: Int, item: T) {
        list[position] = item
        indexChanged = position
        updateType = UpdateType.CHANGE
    }

    fun size(): Int {
        return list.size
    }

    fun applyChange(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        updateType?.notifyChange(adapter, indexChanged)
    }
}