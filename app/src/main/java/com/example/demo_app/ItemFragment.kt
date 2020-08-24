package com.example.demo_app

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.ClassCastException

/**
 * A fragment representing a list of Items.
 */
class ItemFragment : Fragment() {

    private var columnCount = 1
   // val activity: Activity = context as Activity
    private lateinit var event : onItemClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            val activity : Activity = context as Activity
            event = activity as onItemClickListener
        }
        catch (e: ClassCastException) {
            throw ClassCastException(("$activity must override OnMessageRead..."));
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }

                view.adapter = ReminderListAdapter {
                        Toast.makeText(this.context, "click", Toast.LENGTH_SHORT).show()
                        event.showReminderDetails(it)
                }
                val wordViewModel = ViewModelProvider(activity!!).get(ReminderViewModel::class.java)
                wordViewModel.allReminders.observe(activity!!, Observer { rem ->
                    // Update the cached copy of the words in the adapter.
                    rem?.let { (view.adapter as ReminderListAdapter).setReminders(it) }
                })

            }
        }
        return view
    }


    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
                ItemFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }

    interface onItemClickListener {
        fun showReminderDetails(rem:Reminder)
    }

}