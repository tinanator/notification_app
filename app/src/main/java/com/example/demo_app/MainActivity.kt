package com.example.demo_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import java.io.File

const val EXTRA_MESSAGE = "com.example.demo_app.MESSAGE"

private lateinit var recyclerView: RecyclerView
private lateinit var viewAdapter: RecyclerView.Adapter<*>
private lateinit var viewManager: RecyclerView.LayoutManager




class MainActivity : AppCompatActivity(), ItemFragment.onItemClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val bundle = intent.extras
        if (bundle != null) {
            val message = bundle.getParcelable<Reminder>("message")

            if (message != null) {

                val fragment = supportFragmentManager.findFragmentById(R.id.list) as ItemFragment
                (fragment.view as RecyclerView).adapter!!.notifyDataSetChanged()
            }
        }
    }

    override fun showReminderDetails(rem:Reminder) {
        Toast.makeText(this, "name: " + rem.name, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("details", rem)
        startActivity(intent)
    }

    fun addData(view: View) {
        val intent = Intent(this, ReminderCreateWindowActivity::class.java)
        startActivity(intent)
    }



}