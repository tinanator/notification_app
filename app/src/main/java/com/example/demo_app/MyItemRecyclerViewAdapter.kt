package com.example.demo_app

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import com.example.demo_app.dummy.DummyContent.DummyItem
import kotlin.properties.Delegates

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRecyclerViewAdapter(
        private val values: DataModel, private val listener: (Data) -> Unit
)
    : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    private val dataset = values
    private var selectedPos = -1



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataset.getData()[position]
        holder.idView.text = item.getName()
        holder.itemView.setOnClickListener {
            listener(item)
            selectedPos = position
            notifyItemChanged(position)

        }

        if (selectedPos == position) {
            holder.itemView.setBackgroundColor(Color.BLUE)
        }
    }

    override fun getItemCount(): Int = dataset.getSize()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}