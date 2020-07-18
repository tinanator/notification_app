package com.example.demo_app

import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.*
import com.example.demo_app.dummy.DummyContent
import org.json.JSONArray
import org.json.JSONObject
import java.lang.ClassCastException
import java.lang.reflect.Method

/**
 * A fragment representing a list of Items.
 */
class clientsFragment : Fragment() {

    private lateinit var event : onItemClickListener

    private var columnCount = 1
    private val url = "https://randomuser.me/api/?results=15&inc=name,email,picture"
    //var requestQueue = RequestQueue(DiskBasedCache(casheDir, 1024 * 1024), BasicNetwork(HurlStack()))
    lateinit var obj : JSONArray

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_clients_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }


                val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener<JSONObject> { response ->
                    obj = response.getJSONArray("results")
                    adapter = MyItemRecyclerViewAdapter2(obj) { firstName: String, lastName: String, email: String ->
                        event.chooseClient(firstName, lastName, email)

                    }
                    for (i in 0 until obj.length()) {
                        Log.i("tag", obj.getString(i))
                    }

                }, Response.ErrorListener { error ->
                    print("error")
                })

                MySingleton.getInstance(this.context).addToRequestQueue(jsonObjectRequest)

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
            clientsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }

    interface onItemClickListener {
        fun chooseClient(firstName: String, lastName: String, email: String)
    }

}