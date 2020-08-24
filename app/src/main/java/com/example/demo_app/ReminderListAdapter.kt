package com.example.demo_app

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData

import com.example.demo_app.dummy.DummyContent.DummyItem

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class ReminderListAdapter(
      private val listener: (Reminder) -> Unit
)
    : RecyclerView.Adapter<ReminderListAdapter.ViewHolder>() {

   // private var dataset = values
    private var selectedPos = -1
    private var dbData : List<Reminder> = listOf<Reminder>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //val item = dataset.getData()[position]
        val item = dbData[position]
        holder.idView.text = item.name
        holder.itemView.setOnClickListener  {
            listener(item)
            selectedPos = position
            notifyItemChanged(position)

        }
    }

    override fun getItemCount(): Int = dbData.size

    internal fun setReminders(reminders: List<Reminder>) {
        this.dbData = reminders
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}