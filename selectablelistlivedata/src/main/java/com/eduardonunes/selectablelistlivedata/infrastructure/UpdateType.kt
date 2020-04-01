package com.eduardonunes.selectablelistlivedata.infrastructure;

import androidx.recyclerview.widget.RecyclerView

enum class UpdateType {
    UPDATE_LIST {
        override fun notifyChange(
            adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
            indexChanged: Int
        ) = adapter.notifyDataSetChanged()
    },
    INSERT {
        override fun notifyChange(
            adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
            indexChanged: Int
        ) = adapter.notifyItemInserted(indexChanged)
    },
    REMOVE {
        override fun notifyChange(
            adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
            indexChanged: Int
        ) = adapter.notifyItemRemoved(indexChanged)
    },
    CHANGE {
        override fun notifyChange(
            adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
            indexChanged: Int
        ) = adapter.notifyItemChanged(indexChanged)
    };

    abstract fun notifyChange(
        adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
        indexChanged: Int
    )
}