package com.eduardonunes.selectablelistlivedata

import com.eduardonunes.selectablelistlivedata.infrastructure.SelectableItem

class SelectableListLiveData<T : SelectableItem> : ListLiveData<T>() {

    override fun updateList(newList: MutableList<T>) {
        value?.indexChanged = newList.indexOfFirst { it.isActive }
        super.updateList(newList)
    }

    fun singleSelection(item: T) {
        value?.list?.run {
            val lastSelectedIndex = indexOfFirst { it.isActive }
            val newSelectedIndex = indexOf(item)

            when {
                // First selection
                lastSelectedIndex < 0 -> {
                    item.isActive = true
                    setItem(newSelectedIndex, item)
                }
                // Deselection of item
                lastSelectedIndex == newSelectedIndex && newSelectedIndex > -1 -> {
                    item.isActive = false
                    setItem(newSelectedIndex, item)
                }
                // New item selected
                else -> {
                    //Deselect current selection
                    get(lastSelectedIndex).also {
                        it.isActive = false
                        setItem(lastSelectedIndex, it)
                    }
                    //Select new item
                    item.isActive = true
                    setItem(newSelectedIndex, item)
                }
            }
        }
    }

    fun multipleSelection(item: T) {
        value?.list?.indexOf(item)?.also { position ->
            if (position > -1) {
                item.isActive = !item.isActive
                setItem(position, item)
            }
        }
    }

    fun clearSelection(item: T) {
        value?.list?.indexOf(item)?.also { position ->
            if (position > -1) {
                item.isActive = false
                setItem(position, item)
            }
        }
    }

    fun getSelectionList(): List<T> {
        return value?.list?.filter {
            it.isActive
        } ?: emptyList()
    }

}