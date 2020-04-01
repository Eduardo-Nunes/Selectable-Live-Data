package com.eduardonunes.selectablelivedata.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.eduardonunes.selectablelistlivedata.ListLiveData
import com.eduardonunes.selectablelistlivedata.SelectableListLiveData
import com.eduardonunes.selectablelivedata.R
import com.eduardonunes.selectablelivedata.ui.main.models.DumbData
import com.eduardonunes.selectablelivedata.ui.main.models.SelectableDumbData

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val message = MutableLiveData<String?>()
    val dumbListData by lazy { ListLiveData<DumbData>() }
    val multiSelectionData by lazy {
        SelectableListLiveData<SelectableDumbData>()
    }
    val singleSelectionData by lazy {
        SelectableListLiveData<SelectableDumbData>()
    }

    fun initData() {
        val dumbList = mutableListOf<DumbData>()
        val multiList = mutableListOf<SelectableDumbData>()
        val singleList = mutableListOf<SelectableDumbData>()

        repeat(100) {
            dumbList.add(DumbData("dumb item $it"))
            multiList.add(SelectableDumbData("multi item $it"))
            singleList.add(SelectableDumbData("single item $it", it == 0))
        }
        message.postValue(null)
        dumbListData.updateList(dumbList)
        multiSelectionData.updateList(multiList)
        singleSelectionData.updateList(singleList)
    }

    init {
        message.postValue(application.getString(R.string.lista_vazia))
    }
}