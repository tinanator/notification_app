package com.example.demo_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A fragment representing a list of Items.
 */
class ItemFragment : Fragment() {

    private var columnCount = 1

    interface onItemClickListener {
        fun selectItem(s: String?)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
        if (arguments != null) {
            val message = arguments!!.getString("message")


            if (message != null) {
                allData.addData(Data(message))

                //viewManager = LinearLayoutManager(fragment.context)

                //recyclerView.layoutManager = viewManager
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
      //  val dataset = mutableListOf<String>()
       // dataset.add(("lol"))
      //  dataset.add(("kek"))
       // dataset.add(("mda"))
        // Set the adapter

        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }

               // viewAdapter = MyItemRecyclerViewAdapter(myDataset)
                view.adapter = MyItemRecyclerViewAdapter(allData) {
                        Toast.makeText(this.context, "click", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return view
    }

//    fun addItem(item: String) {
//        dataset.add(item)
//   //     val adapter = MyItemRecyclerViewAdapter(dataset)
//        adapter.notifyDataSetChanged()
//
//    }

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
}