package com.eduardonunes.selectablelivedata.ui.main.adapter

import android.view.ViewGroup
import com.eduardonunes.selectablelistlivedata.ListLiveData
import com.eduardonunes.selectablelistlivedata.infrastructure.SelectableAdapter
import com.eduardonunes.selectablelivedata.ui.main.models.SelectableDumbData

class DumbAdapter(
    items: ListLiveData<SelectableDumbData>,
    onItemSelectedListener: (SelectableDumbData) -> Unit
) : SelectableAdapter<SelectableDumbData>(items, onItemSelectedListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DumbViewHolder {
        return DumbViewHolder.create(parent)
    }
}