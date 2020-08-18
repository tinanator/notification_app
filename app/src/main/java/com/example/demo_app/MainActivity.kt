package com.example.demo_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import java.io.File

const val EXTRA_MESSAGE = "com.example.demo_app.MESSAGE"

private lateinit var recyclerView: RecyclerView
private lateinit var viewAdapter: RecyclerView.Adapter<*>
private lateinit var viewManager: RecyclerView.LayoutManager

val allData = DataModel()

lateinit var fragment : ItemFragment

class MainActivity : AppCompatActivity(), ItemFragment.onItemClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {

//        val db = Room.databaseBuilder(
//            applicationContext,
//            AppDatabase::class.java, "myDatabase"
//        ).build()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle = intent.extras
        if (bundle != null) {
            val message = bundle.getParcelable<Data>("message")

            if (message != null) {
                allData.addData(message)
                fragment = supportFragmentManager.findFragmentById(R.id.list) as ItemFragment
                (fragment.view as RecyclerView).adapter!!.notifyDataSetChanged()
            }
        }
    }

    override fun showReminderDetails(data:Data) {
        Toast.makeText(this, "name: " + data.getName(), Toast.LENGTH_SHORT).show()
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("details", data)
        startActivity(intent)
    }

    fun addData(view: View) {
        val intent = Intent(this, ReminderCreateWindowActivity::class.java)
        startActivity(intent)
    }



}