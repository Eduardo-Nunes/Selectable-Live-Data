package com.eduardonunes.selectablelistlivedata

import androidx.lifecycle.LiveData
import com.eduardonunes.selectablelistlivedata.infrastructure.ListHolder

open class ListLiveData<T> : LiveData<ListHolder<T>>(ListHolder()) {

    val size: Int
        get() {
            return value?.size() ?: -1
        }

    open fun updateList(newList: MutableList<T>) {
        value?.updateList(newList)
        postValue(value)
    }

    fun addItem(item: T, position: Int = value?.size() ?: 0) {
        value?.addItem(position, item)
        value = value
    }

    fun removeItemAt(position: Int) {
        value?.removeItemAt(position)
        value = value
    }

    fun setItem(position: Int, item: T) {
        value?.setItem(position, item)
        value = value
    }

    operator fun get(position: Int): T? {
        return value?.list?.get(position)
    }

}