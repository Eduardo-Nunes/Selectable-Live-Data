package com.eduardonunes.selectablelistlivedata.infrastructure

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.eduardonunes.selectablelistlivedata.ListLiveData
import com.eduardonunes.selectablelistlivedata.R

abstract class SelectableAdapter<T : SelectableItem>(
    private val items: ListLiveData<T>,
    private val onItemSelectedListener: (T) -> Unit
) : RecyclerView.Adapter<SelectableItemViewHolder<T>>() {

    private val onItemClickListener = View.OnClickListener {
        val itemPosition = it.getTag(R.string.position) as Int
        items[itemPosition]?.run(onItemSelectedListener)
    }

    override fun onBindViewHolder(holder: SelectableItemViewHolder<T>, position: Int) {
        items[position]?.run(holder::bind)
        holder.setClickListener(onItemClickListener)
    }

    override fun onViewRecycled(holder: SelectableItemViewHolder<T>) {
        holder.onViewRecycled()
        super.onViewRecycled(holder)
    }

    override fun getItemCount(): Int = items.size
}

abstract class SelectableItemViewHolder<T : SelectableItem>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: T)
    abstract fun onViewRecycled()
    fun setClickListener(onItemClickListener: View.OnClickListener) {
        itemView.apply {
            setTag(R.string.position, adapterPosition)
            setOnClickListener(onItemClickListener)
        }
    }
}