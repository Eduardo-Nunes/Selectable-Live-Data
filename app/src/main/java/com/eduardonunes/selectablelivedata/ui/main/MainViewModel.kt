package com.eduardonunes.selectablelivedata.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.eduardonunes.selectablelistlivedata.SelectableListLiveData
import com.eduardonunes.selectablelivedata.R
import com.eduardonunes.selectablelivedata.ui.main.models.SelectableDumbData

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val message = MutableLiveData<String?>()
    val multiSelectionData by lazy {
        SelectableListLiveData<SelectableDumbData>()
    }
    val singleSelectionData by lazy {
        SelectableListLiveData<SelectableDumbData>()
    }

    fun initData() {
        val multiList = mutableListOf<SelectableDumbData>()
        val singleList = mutableListOf<SelectableDumbData>()

        repeat(100) {
            multiList.add(SelectableDumbData("multi item $it"))
            singleList.add(SelectableDumbData("single item $it", it == 0))
        }
        message.postValue(null)
        multiSelectionData.updateList(multiList)
        singleSelectionData.updateList(singleList)
    }

    fun singleSelect(item: SelectableDumbData) {
        if (item.isActive) return
        singleSelectionData.singleSelection(item)
    }

    fun multiSelect(item: SelectableDumbData) {
        multiSelectionData.multipleSelection(item)
    }

    init {
        message.postValue(application.getString(R.string.lista_vazia))
    }
}