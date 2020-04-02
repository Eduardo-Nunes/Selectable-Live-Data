package com.eduardonunes.selectablelivedata.ui.main.adapter

import android.view.View
import android.view.ViewGroup
import com.eduardonunes.selectablelistlivedata.infrastructure.SelectableItemViewHolder
import com.eduardonunes.selectablelivedata.R
import com.eduardonunes.selectablelivedata.ui.extensions.inflate
import com.eduardonunes.selectablelivedata.ui.main.models.SelectableDumbData
import kotlinx.android.synthetic.main.dumb_item.view.*

class DumbViewHolder(itemView: View)
    : SelectableItemViewHolder<SelectableDumbData>(itemView) {

    companion object {
        fun create(parent: ViewGroup): DumbViewHolder {
            return DumbViewHolder(parent.inflate(R.layout.dumb_item))
        }
    }

    override fun bind(item: SelectableDumbData) {
        itemView.filterLabelText.text = item.text
        val backRes = if (item.isActive) R.color.backSelected
        else android.R.color.transparent
        itemView.setBackgroundResource(backRes)
    }

    override fun onViewRecycled() {
        itemView.filterLabelText.text = String()
        itemView.setBackgroundResource(android.R.color.transparent)
    }
}

