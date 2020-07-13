package com.example.demo_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.concurrent.fixedRateTimer

const val EXTRA_MESSAGE = "com.example.demo_app.MESSAGE"

private lateinit var recyclerView: RecyclerView
private lateinit var viewAdapter: RecyclerView.Adapter<*>
private lateinit var viewManager: RecyclerView.LayoutManager

val allData = DataModel()

lateinit var fragment : ItemFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle = intent.extras
        if (bundle != null) {
            //val message = bundle.getString("message")
            val message = bundle.getParcelable<Data>("message")


            if (message != null) {
                allData.addData(message)
                fragment = supportFragmentManager.findFragmentById(R.id.list) as ItemFragment
                //viewManager = LinearLayoutManager(fragment.context)
                (fragment.view as RecyclerView).adapter!!.notifyDataSetChanged()
                //recyclerView.layoutManager = viewManager
            }
        }


        // viewAdapter = MyItemRecyclerViewAdapter(message)

//        viewManager = LinearLayoutManager(this)
//        val myDataset = mutableListOf<String>()
//        myDataset.add("aaaaaa")
//        myDataset.add("bbbbbbb")
//        myDataset.add("cccccccc")
//
//        viewAdapter = MyItemRecyclerViewAdapter(myDataset)
//
//        recyclerView = findViewById<RecyclerView>(R.id.list).apply {
//            layoutManager = viewManager
//            setHasFixedSize(true)
//            adapter = viewAdapter
//        }


    }

//    fun getdata() : MutableList<Data>{
//        return dataset
//    }

    fun addData(view: View) {
        val intent = Intent(this, DisplayMessageActivity::class.java)
        startActivity(intent)
    }


//    fun SendMessage(view: View) {
//        val editText = findViewById<EditText>(R.id.editText)
//        val message = editText.text.toString()
//        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
//            putExtra(EXTRA_MESSAGE, message)
//        }
//        startActivity(intent)
//    }
}