package com.example.basemasterdetailsapplication.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.basemasterdetailsapplication.R
import com.example.basemasterdetailsapplication.domain.DummyData
import kotlinx.android.synthetic.main.list_item.view.*

class ListAdapter(val listener: OnClickListener) :
    ListAdapter<DummyData, DataViewHolder>(DataDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data, listener)

    }
}

class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(data: DummyData, listener: OnClickListener) {

        itemView.textView.text = data.id
        itemView.setOnClickListener {
            listener.onClick(data)
        }
    }

    companion object {
        fun from(parent: ViewGroup): DataViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.list_item, parent, false)
            return DataViewHolder(view)
        }
    }
}

class DataDiffCallback : DiffUtil.ItemCallback<DummyData>() {
    override fun areItemsTheSame(oldItem: DummyData, newItem: DummyData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DummyData, newItem: DummyData): Boolean {
        return oldItem == newItem
    }
}

interface OnClickListener {
    fun onClick(data: DummyData)
}
